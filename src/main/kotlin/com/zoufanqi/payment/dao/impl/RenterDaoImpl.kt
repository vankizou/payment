package com.zoufanqi.payment.dao.impl

import com.qiqinote.util.sql.NamedSQLUtil
import com.zoufanqi.payment.dao.RenterDao
import com.zoufanqi.payment.pojo.Renter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

/**
 * Created by vanki on 2018/11/23 16:12.
 */
@Repository
class RenterDaoImpl @Autowired constructor(
        private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate
) : RenterDao {

    private val rowMapper = BeanPropertyRowMapper(Renter::class.java)

    override fun listOfAll(): MutableList<Renter> {
        val sql = NamedSQLUtil.getSelectSQL(Renter::class)
        return this.namedParameterJdbcTemplate.query("$sql order by money desc", mapOf<String, Any>(), rowMapper)
    }
}