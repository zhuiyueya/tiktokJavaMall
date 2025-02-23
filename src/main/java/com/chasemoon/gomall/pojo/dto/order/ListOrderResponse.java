package com.chasemoon.gomall.pojo.dto.order;

import com.chasemoon.gomall.pojo.entity.Order;
import lombok.Data;

import java.util.List;

@Data
public class ListOrderResponse {
    private List<Order> orders;
}
