package com.zoufanqi.payment.service

import com.zoufanqi.payment.dto.AllocationData
import com.zoufanqi.payment.dto.PaymentResult
import com.zoufanqi.payment.pojo.Renter

/**
 * Created by vanki on 2018/11/23 16:37.
 */
interface PaymentService {
    fun paymentData(): PaymentResult
}