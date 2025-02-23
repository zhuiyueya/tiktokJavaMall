package com.chasemoon.gomall.controller;

import com.alipay.api.AlipayClient;
import com.chasemoon.gomall.service.AlipayCallbackService;
import com.chasemoon.gomall.service.AlipayService;
import com.chasemoon.gomall.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: chasemoon
 * @CreateTime: 2025-02-23
 * @Description:
 * @Version:
 */
@RestController
public class PaymentCallbackController {
    @Autowired
    private AlipayCallbackService alipayCallbackService;
    @PostMapping("/api/payment/notify")
    public String handleAlipayNotify(HttpServletRequest request) {
        return alipayCallbackService.handleAlipayNotify(request);
    }

}
