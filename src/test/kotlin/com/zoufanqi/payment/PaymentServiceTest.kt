package com.zoufanqi.payment

import com.zoufanqi.payment.service.PaymentService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 * Created by vanki on 2018/11/23 20:47.
 */
@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = [PaymentApplication::class])
class PaymentServiceTest {
    @Autowired
    private lateinit var paymentService: PaymentService

    @Test
    fun t1() {
        this.paymentService.paymentData()
    }
}