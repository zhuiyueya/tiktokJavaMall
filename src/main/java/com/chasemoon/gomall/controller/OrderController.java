package com.chasemoon.gomall.controller;

import com.alipay.api.AlipayApiException;
import com.chasemoon.gomall.common.Result;
import com.chasemoon.gomall.pojo.dto.order.*;
import com.chasemoon.gomall.service.AlipayService;
import com.chasemoon.gomall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private AlipayService alipayService;
    @PostMapping("/place")
    public Result<Map<String,String>>placeOrder(Authentication authentication, @RequestBody PlaceOrderRequest placeOrderRequest) {
        int userId=Integer.parseInt(authentication.getPrincipal().toString());
        placeOrderRequest.setUserId(userId);
        PlaceOrderResponse placeOrderResponse=orderService.placeOrder(placeOrderRequest);

        try{
            String paymentForm= alipayService.createPayment(placeOrderResponse.getOrder());
            return Result.success(Collections.singletonMap("paymentForm",paymentForm));
        }catch(AlipayApiException e){
            throw new RuntimeException(e);
        }

    }
    @PostMapping("markPaid")
    public Result<MarkOrderPaidResponse>markOrderPaid(Authentication authentication,@RequestBody MarkOrderPaidRequest markOrderPaidRequest) {
        int userId=Integer.parseInt(authentication.getPrincipal().toString());
        markOrderPaidRequest.setUserId(userId);
        return Result.success(orderService.markOrderPaid(markOrderPaidRequest));

    }
    @GetMapping("/list")
    public Result<ListOrderResponse>listOrder(Authentication authentication) {
        ListOrderRequest listOrderRequest=new ListOrderRequest();
        listOrderRequest.setUserId(Integer.parseInt(authentication.getPrincipal().toString()));
        return Result.success(orderService.listOrder(listOrderRequest));

    }

    //获取订单状态，可用在前端支付完成后轮询后端订单状态
    @PostMapping("/getOrderStatus")
    public Result<GetOrderStatusResponse>getOrderStatus(@RequestBody GetOrderStatusRequest getOrderStatusRequest) {
        return Result.success(orderService.getOrderStatus(getOrderStatusRequest));
    }


}
