package com.zoufanqi.payment.dto

import java.math.BigDecimal

/**
 * Created by vanki on 2018/11/23 18:02.
 */
class RecordJson(
        val renters: MutableMap<Int, BigDecimal>,
        var leftMoney: BigDecimal
)