package com.zoufanqi.payment.dao

import com.zoufanqi.payment.dto.RecordJson
import com.zoufanqi.payment.pojo.PaymentRecord

/**
 * Created by vanki on 2018/11/23 16:12.
 */
interface PaymentRecordDao {
    fun updateRecord(id: Int, record: RecordJson): Int

    fun listOfAll(): MutableList<PaymentRecord>
}