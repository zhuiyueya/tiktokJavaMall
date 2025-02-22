package com.chasemoon.gomall.pojo.dto.cart;

import lombok.Data;

@Data
public class EmptyCartRequest {
    private int userId;

    public EmptyCartRequest(int userId) {
        this.userId = userId;
    }

    public EmptyCartRequest() {
    }
}
