package com.chasemoon.gomall.pojo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="product_id")
    private int productId;
    @Column()
    private int quantity;
    @ManyToOne()
    @JoinColumn(name="order_id")
    @JsonBackReference//标记为从端，不进行序列化，即搜索时不再对应的另一张表
    private Order order;
    private float cost;
}
