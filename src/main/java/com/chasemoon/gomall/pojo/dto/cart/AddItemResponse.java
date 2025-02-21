package com.chasemoon.gomall.pojo.dto.cart;

import lombok.Data;

@Data
public class AddItemResponse {
    private int productId;
    private int quantity;

    public AddItemResponse(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
