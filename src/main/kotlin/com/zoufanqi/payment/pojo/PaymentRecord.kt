package com.zoufanqi.payment.pojo

import com.zoufanqi.payment.dto.RecordJson
import java.math.BigDecimal
import java.util.*

/**
 * Created by vanki on 2018/11/23 16:06.
 */
class PaymentRecord {
    var id: Int = 0

    lateinit var money: BigDecimal

    var day: Date? = null

    var record: RecordJson? = null
}