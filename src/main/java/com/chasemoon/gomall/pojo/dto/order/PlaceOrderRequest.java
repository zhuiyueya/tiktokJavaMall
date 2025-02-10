package com.chasemoon.gomall.pojo.dto.order;

import com.chasemoon.gomall.pojo.entity.Address;
import com.chasemoon.gomall.pojo.entity.OrderItem;

import java.util.List;

public class PlaceOrderRequest {
    private int userId;
    private String userCurrency;
    private Address address;
    private String email;
    private List<OrderItem> orderItems;
}
