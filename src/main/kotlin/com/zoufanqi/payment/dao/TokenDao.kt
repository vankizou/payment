package com.zoufanqi.payment.dao

import com.zoufanqi.payment.pojo.Token

/**
 * Created by vanki on 2018/11/23 16:12.
 */
interface TokenDao {
    fun upsertToken(token: Token): Int

    fun getToken(): Token?
}