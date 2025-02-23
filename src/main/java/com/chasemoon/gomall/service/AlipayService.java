package com.chasemoon.gomall.service;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.chasemoon.gomall.pojo.entity.Order;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: chasemoon
 * @CreateTime: 2025-02-23
 * @Description:
 * @Version:
 */

@Service
public class AlipayService {
    @Autowired
    private AlipayClient alipayClient;



    @Value("${alipay.notify-url}")
    private String notifyUrl;

    @Value("${alipay.return-url}")
    private String returnUrl;



    public String createPayment(Order order)throws AlipayApiException {
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(notifyUrl);
        request.setReturnUrl(returnUrl);

        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", order.getOrderId());
        bizContent.put("total_amount",order.getTotalCost());
        bizContent.put("subject","订单支付"+order.getOrderId());
        bizContent.put("product_code","FAST_INSTANT_TRADE_PAY");

        request.setBizContent(bizContent.toString());

        AlipayTradePagePayResponse response=alipayClient.pageExecute(request);
        return response.getBody();

    }

}
