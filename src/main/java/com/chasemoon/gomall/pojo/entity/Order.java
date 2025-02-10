package com.chasemoon.gomall.pojo.entity;

import java.util.List;

public class Order {
    private List<OrderItem> orderItems;
    private String orderId;
    private int userId;
    private String userCurrency;
    private Address address;
    private String email;
    private int created_at;
}
