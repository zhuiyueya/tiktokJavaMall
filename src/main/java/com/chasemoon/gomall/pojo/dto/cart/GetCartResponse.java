package com.chasemoon.gomall.pojo.dto.cart;

import com.chasemoon.gomall.pojo.entity.Cart;
import lombok.Data;

@Data
public class GetCartResponse {
    private Cart cart;

    public GetCartResponse(Cart cart) {
        this.cart = cart;
    }
}
