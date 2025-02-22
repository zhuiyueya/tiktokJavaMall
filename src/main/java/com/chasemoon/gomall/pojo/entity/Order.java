package com.chasemoon.gomall.pojo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="orders")
@Data
public class Order {
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;
    @Id
    private String orderId;
    //private int userId;
    @Column(name="user_currency")
    private String userCurrency;//下单货币类型
    @Embedded
//    @AttributeOverrides({ // 映射字段名到数据库列
//            @AttributeOverride(name = "streetAddress", column = @Column(name = "street_address")),
//            @AttributeOverride(name = "zipCode", column = @Column(name = "zip_code"))
//    })
    private OrderAddress address;
    @Column
    private String email;
    @Column
    private Date created_at;
}
