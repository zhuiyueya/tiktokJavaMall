package com.chasemoon.gomall.pojo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="cartItems")
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_item_id")
    private int cartItemId;
    @Column(name="product_id")
    private int productId;
    @Column()
    private int quantity;
    @ManyToOne
    @JoinColumn(name="cart_id")
    private Cart cart;

}
