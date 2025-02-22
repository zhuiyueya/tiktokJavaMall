package com.chasemoon.gomall.pojo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference//标记为从端，不进行序列化，即搜索时不再对应的另一张表
    private Cart cart;

}
