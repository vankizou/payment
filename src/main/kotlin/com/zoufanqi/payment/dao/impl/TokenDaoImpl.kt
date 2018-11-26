package com.zoufanqi.payment.dao.impl

import com.qiqinote.util.sql.NamedSQLUtil
import com.zoufanqi.payment.dao.TokenDao
import com.zoufanqi.payment.pojo.Renter
import com.zoufanqi.payment.pojo.Token
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.util.*

/**
 * Created by vanki on 2018/11/23 16:12.
 */
@Repository
class TokenDaoImpl @Autowired constructor(
        private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate
) : TokenDao {
    private val rowMapper = BeanPropertyRowMapper(Token::class.java)

    override fun upsertToken(token: Token): Int {
        val old = if (token.id == null) null else this.getTokenById(token.id!!)
        return if (old == null) {
            val params = mapOf(
                    "token" to token.token,
                    "start_date" to token.startDate,
                    "day" to token.day
            )
            val sql = NamedSQLUtil.getInsertSQL(Token::class, params)
            this.namedParameterJdbcTemplate.update(sql, params)
        } else {
            val params = LinkedHashMap<String, Any?>()
            params["token"] = token.token
            params["start_date"] = token.startDate
            params["day"] = token.day
            params["id"] = token.id!!
            val sql = NamedSQLUtil.getUpdateSQL(Token::class, params, params.size - 1 - 1)
            this.namedParameterJdbcTemplate.update(sql, params)
        }
    }

    override fun getToken(): Token? {
        val sql = NamedSQLUtil.getSelectSQL(Token::class)
        val tokens = this.namedParameterJdbcTemplate.query("$sql order by id desc limit 1", rowMapper)
        return if (tokens.isEmpty()) null else tokens[0]
    }

    private fun getTokenById(id: Int): Token? {
        val sql = NamedSQLUtil.getSelectSQL(Token::class)
        val tokens = this.namedParameterJdbcTemplate.query("$sql where id=$id", rowMapper)
        return if (tokens.isEmpty()) null else tokens[0]
    }
}