package com.zoufanqi.payment.dto

import com.zoufanqi.payment.pojo.PaymentRecord
import com.zoufanqi.payment.pojo.Renter

/**
 * Created by vanki on 2018-11-24 09:54.
 */
data class PaymentResult(
        val renters: Map<Int, Renter>,
        val allocationDataMap: Map<Int, AllocationData>,
        val records: List<PaymentRecord>
)