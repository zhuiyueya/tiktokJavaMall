package com.chasemoon.gomall.pojo.dto.order;

import com.chasemoon.gomall.pojo.entity.Order;
import com.chasemoon.gomall.pojo.entity.OrderResult;
import lombok.Data;

@Data
public class PlaceOrderResponse {
    private Order order;
}
