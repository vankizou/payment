package com.zoufanqi.payment.pojo

import java.math.BigDecimal
import java.util.*

/**
 * Created by vanki on 2018/11/23 16:10.
 */
class Renter {
    var id: Int? = null

    lateinit var name: String

    lateinit var money: BigDecimal

    var endDate: Date? = null

    var qrImg: LinkedHashMap<String, String>? = null
}