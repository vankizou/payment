package com.zoufanqi.payment.dao

import com.zoufanqi.payment.pojo.Renter

/**
 * Created by vanki on 2018/11/23 16:12.
 */
interface RenterDao {
    fun listOfAll(): MutableList<Renter>
}