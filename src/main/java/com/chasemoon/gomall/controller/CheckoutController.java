package com.chasemoon.gomall.controller;

import com.chasemoon.gomall.common.Result;
import com.chasemoon.gomall.pojo.dto.checkout.CheckoutRequest;
import com.chasemoon.gomall.pojo.dto.checkout.CheckoutResponse;
import com.chasemoon.gomall.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {
    @Autowired
    private CheckoutService checkoutService;
    @PostMapping
    public Result<CheckoutResponse> check(Authentication authentication) {
        int userId=Integer.parseInt(authentication.getPrincipal().toString());
        CheckoutRequest checkoutRequest=new CheckoutRequest();
        checkoutRequest.setUserId(userId);
        return Result.success(checkoutService.checkout(checkoutRequest));
    }
}
