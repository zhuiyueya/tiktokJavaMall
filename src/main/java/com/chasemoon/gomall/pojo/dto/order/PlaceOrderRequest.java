package com.chasemoon.gomall.pojo.dto.order;

import com.chasemoon.gomall.pojo.entity.Address;
import com.chasemoon.gomall.pojo.entity.OrderItem;
import com.chasemoon.gomall.pojo.entity.OrderProduct;
import lombok.Data;

import java.util.List;

@Data
public class PlaceOrderRequest {
    private String userName;
    private String email;
    private List<OrderProduct>orderProducts;
    private float totalCost;
    private Long addressId;

    //非前端body携带数据
    private int userId;

}
