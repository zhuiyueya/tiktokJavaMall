package com.chasemoon.gomall.controller;

import com.chasemoon.gomall.common.Result;
import com.chasemoon.gomall.pojo.dto.order.*;
import com.chasemoon.gomall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("markPaied")
    public Result<MarkOrderPaidResponse>markOrderPaid(Authentication authentication,@RequestBody MarkOrderPaidRequest markOrderPaidRequest) {
        int userId=Integer.parseInt(authentication.getPrincipal().toString());
        return Result.success(orderService.markOrderPaid(markOrderPaidRequest,userId));

    }
    @GetMapping("/list")
    public Result<ListOrderResponse>listOrder(Authentication authentication) {
        ListOrderRequest listOrderRequest=new ListOrderRequest();
        listOrderRequest.setUserId(Integer.parseInt(authentication.getPrincipal().toString()));
        return Result.success(orderService.listOrder(listOrderRequest));

    }


}
