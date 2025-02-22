package com.chasemoon.gomall.pojo.dto.order;

import com.chasemoon.gomall.pojo.entity.Address;
import com.chasemoon.gomall.pojo.entity.OrderItem;
import lombok.Data;

import java.util.List;

@Data
public class PlaceOrderRequest {

    private Long addressId;

}
