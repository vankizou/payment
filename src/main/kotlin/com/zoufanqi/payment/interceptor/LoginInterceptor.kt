package com.zoufanqi.payment.interceptor

import com.zoufanqi.payment.service.TokenService
import com.zoufanqi.payment.util.CookieUtil
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by vanki on 2018/1/24 14:19.
 */
@Component
class LoginInterceptor @Autowired constructor(
        private val tokenService: TokenService
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        request.characterEncoding = "UTF-8"
        response.characterEncoding = "UTF-8"
        response.contentType = "application/json;charset=UTF-8"

        val uri = request.requestURI
        if (uri.startsWith("/payment/token")) return true

        val tokenName = "token"
        val paramToken = request.getParameter(tokenName)
        val cookieToken =
                if (StringUtils.isBlank(paramToken)) {
                    CookieUtil.getCookie(request, tokenName)
                } else {
                    CookieUtil.setCookie(response, tokenName, paramToken)
                    paramToken
                }
        if (StringUtils.isBlank(cookieToken) || this.tokenService.getToken() != cookieToken) {
            this.redirectToTokenHtml(response)
            return false
        }

        return true
    }

    private fun redirectToTokenHtml(res: HttpServletResponse) {
        res.sendRedirect("/payment/token")
    }

}