package com.chasemoon.gomall.pojo.dto.cart;

import com.chasemoon.gomall.pojo.entity.CartItem;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AddItemRequest {
    private int productId;
    private int quantity;
}
