package com.chasemoon.gomall.pojo.dto.order;

import com.chasemoon.gomall.pojo.entity.Order;

/**
 * @Author: chasemoon
 * @CreateTime: 2025-02-23
 * @Description:
 * @Version:
 */

public class GetOrderStatusResponse {
    Order.OrderStatus orderStatus;

    public GetOrderStatusResponse(Order.OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
