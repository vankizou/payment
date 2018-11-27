package com.zoufanqi.payment.dao.impl

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.TypeReference
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
        return this.namedParameterJdbcTemplate.query("$sql order by money desc", mapOf<String, Any>()) { rs, _ ->
            val r = Renter()
            r.id = rs.getInt("id")
            r.name = rs.getString("name")
            r.money = rs.getBigDecimal("money")
            r.endDate = rs.getDate("end_date")
            r.qrImg = JSON.parseObject(rs.getString("qr_img"), object : TypeReference<LinkedHashMap<String, String>>() {})
            r
        }
    }
}