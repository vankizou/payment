package com.zoufanqi.payment.util

import org.apache.commons.lang3.time.DateFormatUtils
import java.text.SimpleDateFormat
import java.util.*
import java.util.Locale


/**
 * Created by vanki on 2018/1/24 15:35.
 */
object DateUtil {
    private val sdfDatetime = "yyyy-MM-dd HH:mm:ss"
    private val sdfDate = "yyyy-MM-dd"
    private val sdfDayId = "yyyyMMdd"

    fun parseDate(dateStr: String?): Date? {
        return SimpleDateFormat(sdfDate).parse(dateStr ?: return null)
    }

    fun parseDatetime(datetimeStr: String?): Date? {
        return SimpleDateFormat(sdfDatetime).parse(datetimeStr ?: return null)
    }

    fun formatDate(date: Date?): String? {
        return DateFormatUtils.format(date ?: return null, sdfDate)
    }

    fun formatDatetime(date: Date?): String? {
        return DateFormatUtils.format(date ?: return null, sdfDatetime)
    }

    fun getBetweenDays(start: Date, end: Date): Int {
        return ((end.time - start.time) / (3600 * 24 * 1000)).toInt()
    }

    fun dayId(diffDay: Int = 0) = DateFormatUtils.format(getDiffDate(diffDay), sdfDayId).toInt()

    fun formatDate(diffDay: Int = 0) = DateFormatUtils.format(getDiffDate(diffDay), sdfDate)

    fun getDiffDate(diffDay: Int, date: Date? = null): Date {
        val cal = Calendar.getInstance(Locale.CHINA)
        if (date == null) {
            cal.timeInMillis = System.currentTimeMillis()
        } else {
            cal.time = date
        }
        cal.add(Calendar.DAY_OF_MONTH, diffDay)
        return cal.time
    }

    fun getCustomDate(date: Date, hour: Int, minute: Int, second: Int): Date {
        val cal = Calendar.getInstance(Locale.CHINA)
        cal.time = date
        cal.set(Calendar.HOUR_OF_DAY, hour)
        cal.set(Calendar.MINUTE, minute)
        cal.set(Calendar.SECOND, second)
        return cal.time
    }


    @JvmStatic
    fun main(args: Array<String>) {
//        println(parseDate("2018-11-12 10:23:22"))
//        println(parseDatetime("2018-11-12 10:23:22"))
//        println(formatDate(Date()))
//        println(formatDatetime(Date()))

//        println(getBetweenDays(parseDatetime("2018-08-15 14:23:22")!!, Date()))

        println(dayId(7))
//        println(formatDate(-7))

        println(formatDatetime(getCustomDate(getDiffDate(1), 20, 0, 61)))
        println(formatDatetime(getCustomDate(getDiffDate(1), 3, -10, 0)))
    }
}