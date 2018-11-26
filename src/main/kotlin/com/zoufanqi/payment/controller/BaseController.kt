package com.zoufanqi.payment.controller

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by vanki on 2018/7/19 10:19.
 */
open class BaseController {
    protected val log = LoggerFactory.getLogger(this.javaClass)!!

    @Autowired
    protected lateinit var request: HttpServletRequest
    @Autowired
    protected lateinit var response: HttpServletResponse

}