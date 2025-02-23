package com.chasemoon.gomall.service;

import com.chasemoon.gomall.pojo.dto.cart.GetCartRequest;
import com.chasemoon.gomall.pojo.dto.cart.GetCartResponse;
import com.chasemoon.gomall.pojo.dto.checkout.CheckoutRequest;
import com.chasemoon.gomall.pojo.dto.checkout.CheckoutResponse;
import com.chasemoon.gomall.pojo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CheckoutService {
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ProductService productService;
    public CheckoutResponse checkout(CheckoutRequest checkoutRequest) {
        //访问其他业务层获取对应的数据
        //user相关
        String userName=userService.getUserNameByUserId(checkoutRequest.getUserId());
        String email=userService.getEmialByUserId(checkoutRequest.getUserId());
        //地址相关
        List<Address> addresses=addressService.getAllAddressByUserId(checkoutRequest.getUserId());
        //购物车和商品相关
        List<OrderProduct>products=cartService.getOrderProductsByUserId(checkoutRequest.getUserId());
        //总价格
        float totalCost=0;
        for(OrderProduct product:products){
            totalCost+=product.getPrice()*product.getQuantity();
        }

        return new CheckoutResponse(userName,email,addresses,products,totalCost);
    }
}
