package com.zoufanqi.payment.dao.impl

import com.alibaba.fastjson.JSON
import com.qiqinote.util.sql.NamedSQLUtil
import com.zoufanqi.payment.dao.PaymentRecordDao
import com.zoufanqi.payment.dto.RecordJson
import com.zoufanqi.payment.pojo.PaymentRecord
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

/**
 * Created by vanki on 2018/11/23 16:12.
 */
@Repository
class PaymentRecordDaoImpl @Autowired constructor(
        private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate
) : PaymentRecordDao {

    private val rowMapper = BeanPropertyRowMapper(PaymentRecord::class.java)

    override fun updateRecord(id: Int, record: RecordJson): Int {
        val params = linkedMapOf<String, Any?>("record" to JSON.toJSONString(record), "id" to id)
        val sql = NamedSQLUtil.getUpdateSQL(PaymentRecord::class, params, params.size - 1 - 1)
        return this.namedParameterJdbcTemplate.update(sql, params)
    }

    override fun listOfAll(): MutableList<PaymentRecord> {
        val sql = NamedSQLUtil.getSelectSQL(PaymentRecord::class)
        return this.namedParameterJdbcTemplate.query("$sql order by day asc", mapOf<String, Any>())
        { rs, _ ->
            val record = PaymentRecord()
            record.id = rs.getInt("id")
            record.day = rs.getDate("day")
            record.money = rs.getBigDecimal("money")

            val r = rs.getString("record")
            record.record = if (StringUtils.isBlank(r)) null else JSON.parseObject(r, RecordJson::class.java)

            record
        }
    }
}