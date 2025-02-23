package com.chasemoon.gomall.pojo.dto.checkout;

import com.chasemoon.gomall.pojo.entity.Address;
import com.chasemoon.gomall.pojo.entity.OrderProduct;
import lombok.Data;

import java.util.List;

@Data
public class CheckoutResponse {
    private String userName;
    private String email;
    private List<Address> address;
    private List<OrderProduct>orderProducts;
    private float totalCost;
    //private String orderId;
    //private String transactionId;

    public CheckoutResponse(String userName, String email, List<Address> address, List<OrderProduct> orderProducts, float totalCost) {
        this.userName = userName;
        this.email = email;
        this.address = address;
        this.orderProducts = orderProducts;
        this.totalCost = totalCost;
    }
}
