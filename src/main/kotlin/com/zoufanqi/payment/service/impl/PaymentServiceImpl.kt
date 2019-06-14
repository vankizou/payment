package com.zoufanqi.payment.service.impl

import com.zoufanqi.payment.dao.PaymentRecordDao
import com.zoufanqi.payment.dao.RenterDao
import com.zoufanqi.payment.dto.AllocationData
import com.zoufanqi.payment.dto.PaymentResult
import com.zoufanqi.payment.dto.RecordJson
import com.zoufanqi.payment.pojo.PaymentRecord
import com.zoufanqi.payment.pojo.Renter
import com.zoufanqi.payment.service.PaymentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.concurrent.locks.ReentrantLock

/**
 * Created by vanki on 2018/11/23 16:37.
 */
@Service
class PaymentServiceImpl @Autowired constructor(
        private val renterDao: RenterDao,
        private val paymentRecordDao: PaymentRecordDao
) : PaymentService {

    val lock = ReentrantLock()

    override fun paymentData(): PaymentResult {
        val renters = this.renterDao.listOfAll().map { it.id!! to it }.toMap()
        val records = this.paymentRecordDao.listOfAll()

        val allocationDataMap = mutableMapOf<Int, AllocationData>() // 已分配的统计
        val preRecords = mutableListOf<PaymentRecord>()  // 新增还未分配的

        /**
         * 统计历史
         */
        records.forEach {
            if (it.record == null) {
                preRecords.add(it)
            } else {
                this.countHistoryRecord(allocationDataMap, it)
            }
        }

        /**
         * 分配新增
         */
        try {
            lock.lock()
            preRecords.forEach {
                this.allocation(renters, allocationDataMap, it)
            }
        } finally {
            lock.unlock()
        }

        return PaymentResult(renters, allocationDataMap, records)
    }

    /**
     * 分配
     */
    private fun allocation(renters: Map<Int, Renter>, allocationDataMap: MutableMap<Int, AllocationData>, record: PaymentRecord) {
        if (record.record != null) return

        val jsonRenterMap = mutableMapOf<Int, BigDecimal>()
        val recordJson = RecordJson(jsonRenterMap, record.money)

        /**
         * 排除已经中止或分配完的人
         */
        val realRenters = mutableMapOf<Int, Renter>()   // 排除已经还完的人
        var leftTotalMoney = BigDecimal.ZERO  // 所有人剩余总欠款
        renters.forEach {
            val renterId = it.key
            val renter = it.value
            if (renter.endDate != null && record.day!!.after(renter.endDate)) return@forEach

            val ad = allocationDataMap.getOrDefault(renterId, AllocationData(BigDecimal.ZERO))    // 之前分配过的钱汇总
            val totalMoney = renter.money

            if (totalMoney <= ad.currMoney) return@forEach // 已分配过且钱已还完

            realRenters[renterId] = it.value
            leftTotalMoney += totalMoney.minus(ad.currMoney)
            allocationDataMap[renterId] = ad
        }
        if (leftTotalMoney <= BigDecimal.ZERO) return

        /**
         * 比例分配
         */
        realRenters.forEach {
            val renterId = it.key
            val renter = it.value
            val ad = allocationDataMap[renterId]!!
            val left = renter.money - ad.currMoney // 剩余未还款

            val ratio = left.divide(leftTotalMoney, 4, RoundingMode.HALF_UP)   // 比例
            val ratioMoney = ratio * record.money  // 按比例本次能得到的钱

            ad.currMoney = ad.currMoney + ratioMoney
            allocationDataMap[renterId] = ad

            recordJson.renters[renterId] = recordJson.renters.getOrDefault(renterId, BigDecimal.ZERO) + ratioMoney
            recordJson.leftMoney = recordJson.leftMoney - ratioMoney
        }
        record.record = recordJson
        this.paymentRecordDao.updateRecord(record.id, recordJson)
    }

    /**
     * 统计已分配的
     */
    private fun countHistoryRecord(allocationDataMap: MutableMap<Int, AllocationData>, record: PaymentRecord) {
        val recordJson = record.record ?: return

        recordJson.renters.forEach {
            val renterId = it.key
            val ad = allocationDataMap.getOrDefault(renterId, AllocationData(BigDecimal.ZERO))    // 之前分配过的钱汇总
            ad.addCurrMoney(it.value)
            allocationDataMap[renterId] = ad
        }
    }
}