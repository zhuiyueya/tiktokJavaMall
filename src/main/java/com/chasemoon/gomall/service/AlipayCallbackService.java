package com.chasemoon.gomall.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
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
public class AlipayCallbackService {
    @Value("${alipay.alipay-public-key}")
    private String alipayPublicKey;
    @Autowired
    private OrderService orderService;
    public String handleAlipayNotify(HttpServletRequest request) {
        Map<String,String> params=convertRequestParamsToMap(request);
        try{
            boolean signVerified= AlipaySignature.rsaCheckV1(params,alipayPublicKey,"UTF-8","RSA2");
            if(!signVerified){
                return "failure";
            }

            String tradeStatus=params.get("trade_status");
            String orderId=params.get("out_trade_no");
            if("TRADE_SUCCESS".equals(tradeStatus)){
                orderService.updateOrderStatus(orderId, Order.OrderStatus.PAID);
            }
            return "success";
        }catch(AlipayApiException e){
            return "failure";
        }
    }
    private Map<String,String>convertRequestParamsToMap(HttpServletRequest request){
        Map<String,String>map=new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for(String name:requestParams.keySet()){
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr=(i==values.length-1)?valueStr+values[i]:valueStr+values[i]+",";
            }
            map.put(name,valueStr);
        }
        return map;
    }
}
