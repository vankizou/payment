package com.zoufanqi.payment.service.impl

import com.zoufanqi.payment.dao.TokenDao
import com.zoufanqi.payment.pojo.Token
import com.zoufanqi.payment.service.TokenService
import com.zoufanqi.payment.util.DateUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

/**
 * Created by vanki on 2018/11/23 16:36.
 */
@Service
class TokenServiceImpl @Autowired constructor(
        private val tokenDao: TokenDao
) : TokenService {
    override fun getToken(): String {
        val token = this.tokenDao.getToken()

        val currDate = Date()
        if (token != null) {
            val bd = DateUtil.getBetweenDays(token.startDate, currDate)
            if (bd <= token.day) return token.token
        }

        val nToken = Token()
        nToken.id = token?.id
        token?.day?.let { nToken.day = it }
        nToken.startDate = currDate
        nToken.token = UUID.randomUUID().toString().replace("-", "")

        this.tokenDao.upsertToken(nToken)
        return nToken.token
    }

}