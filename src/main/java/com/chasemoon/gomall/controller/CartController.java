package com.chasemoon.gomall.controller;

import com.chasemoon.gomall.common.Result;
import com.chasemoon.gomall.pojo.dto.cart.AddItemRequest;
import com.chasemoon.gomall.pojo.dto.cart.AddItemResponse;
import com.chasemoon.gomall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.Authenticator;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @PostMapping("/addItem")
    public Result<AddItemResponse> addItem(Authentication authentication, @RequestBody AddItemRequest addItemRequest) {
        //提取出security context中的用户id信息
        int userId = Integer.parseInt(authentication.getPrincipal().toString());

        return Result.success(cartService.addItem(userId,addItemRequest));
    }

}
