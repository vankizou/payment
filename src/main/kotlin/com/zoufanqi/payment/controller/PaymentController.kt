package com.zoufanqi.payment.controller

import com.zoufanqi.payment.service.PaymentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by vanki on 2018-11-24 09:23.
 */
@Controller
@RequestMapping("/")
class PaymentController @Autowired constructor(
        private val paymentService: PaymentService
) : BaseController() {
    private val df = DecimalFormat(",##0.00")

    @RequestMapping("/payment/list")
    fun payment(): ModelAndView {
        val result = this.paymentService.paymentData()

        /**
         * 组装人员基本信息
         */
        var totalMoney = BigDecimal.ZERO
        val changedRenters = mutableListOf<Map<String, Any>>()
        result.renters.forEach {
            val renterId = it.key
            val renter = it.value

            changedRenters.add(
                    mapOf(
                            "name" to renter.name,
                            "money" to df.format(renter.money),
                            "currMoney" to
                                    if (result.allocationDataMap.containsKey(renterId))
                                        df.format(result.allocationDataMap[renterId]!!.currMoney)
                                    else
                                        "0.00",
                            "leftMoney" to
                                    df.format(renter.money - (result.allocationDataMap[renterId]?.currMoney
                                            ?: BigDecimal.ZERO)),
                            "qrImg" to (renter.qrImg ?: Collections.EMPTY_MAP)
                    ))
            totalMoney += renter.money
        }

        /**
         * 组装历史分配记录额
         */
        var paymentMoney = BigDecimal.ZERO
        val changedRecords = mutableListOf<Map<String, Any>>()
        result.records.reversed().forEach { record ->
            val currRecordGetMoneys = mutableListOf<String>()   // 本次分配获取得的金额
            val recordJson = record.record!!
            result.renters.keys.forEach {
                currRecordGetMoneys.add(if (recordJson.renters.containsKey(it)) df.format(recordJson.renters[it]) else "-")
            }

            changedRecords.add(
                    mapOf(
                            "day" to SimpleDateFormat("yyyyMMdd").format(record.day!!),
                            "money" to df.format(record.money),
                            "moneyGets" to currRecordGetMoneys
                    )
            )
            paymentMoney += record.money
        }

        val mv = ModelAndView("payment")
        mv.addObject("totalMoney", df.format(totalMoney))
        mv.addObject("paymentMoney", df.format(paymentMoney))
        mv.addObject("leftMoney", df.format(totalMoney - paymentMoney))
        mv.addObject("renters", changedRenters)
        mv.addObject("records", changedRecords)
        return mv
    }

    @RequestMapping("/payment/token")
    fun tokenHtml() = "token"
}