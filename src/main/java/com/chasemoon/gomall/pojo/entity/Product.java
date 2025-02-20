package com.chasemoon.gomall.pojo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="product_name")
    private String productName;
    @Column
    private float price;
    @Column
    private String description;
    @Column
    private String picture;
    //@Column
    //private List<String> categories;
}
