package com.zoufanqi.payment.exception

import org.apache.log4j.Logger
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by vanki on 2018/1/22 18:48.
 */
//@ControllerAdvice
class ExceptionHandler {
    private val log = Logger.getLogger(ExceptionHandler::class.java)

    //    @ExceptionHandler(Exception::class)
    fun exception(ex: Exception, request: HttpServletRequest, response: HttpServletResponse) {
        /*if (ex is QiqiNoteException) {
            log.error(ex.codeEnum)

            return when (ex.codeEnum) {
                CodeEnum.NOT_LOGIN_HTML -> ModelAndView(WebPageEnum.login.url)
                CodeEnum.NOT_FOUND -> "redirect:/404.html"
                CodeEnum.SYSTEM_ERROR -> "redirect:/500.html"
                else -> {
                    WebUtil.printResponseData(response, ResultVO(ex.codeEnum))
                    null
                }
            }
        } else if (ex is IllegalArgumentException) {
            log.error("参数异常", ex)
            WebUtil.printResponseData(response, ResultVO(CodeEnum.PARAM_ERROR))
            return null
        }
        log.error("", ex)
        when (response.status) {
            HttpStatus.NOT_FOUND.value() -> return "redirect:/404.html"
            HttpStatus.BAD_REQUEST.value() -> return "redirect:/404.html"
            HttpStatus.BAD_REQUEST.value() -> return "redirect:/500.html"
        }
        return "redirect:/500.html"*/
    }
}