package com.zoufanqi.payment.pojo

import java.util.*

/**
 * Created by vanki on 2018-11-26 15:31.
 */
class Token {
    var id: Int? = null

    lateinit var token: String

    lateinit var startDate: Date

    var day: Int = 3
}