package com.zoufanqi.payment.dto

import java.math.BigDecimal

/**
 * 用户已分配的统计
 *
 * Created by vanki on 2018/11/23 16:53.
 */
class AllocationData(
        var currMoney: BigDecimal = BigDecimal.ZERO // 已经拿到的钱
) {
    fun addCurrMoney(m: BigDecimal) {
        this.currMoney = this.currMoney + m
    }
}