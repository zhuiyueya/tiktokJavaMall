package com.chasemoon.gomall.controller;

import com.chasemoon.gomall.common.Result;
import com.chasemoon.gomall.pojo.dto.order.*;
import com.chasemoon.gomall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/place")
    public Result<PlaceOrderResponse>placeOrder(Authentication authentication, @RequestBody PlaceOrderRequest placeOrderRequest) {
        int userId=Integer.parseInt(authentication.getPrincipal().toString());
        return Result.success(orderService.placeOrder(placeOrderRequest,userId));
    }
    public Result<MarkOrderPaidResponse>markOrderPaid(MarkOrderPaidRequest markOrderPaidRequest) {
        return null;
    }
    public Result<ListOrderResponse>listOrder(ListOrderRequest listOrderRequest) {
        return null;
    }
}
