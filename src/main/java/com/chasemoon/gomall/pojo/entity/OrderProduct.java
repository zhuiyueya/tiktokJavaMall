package com.chasemoon.gomall.pojo.entity;

import jakarta.persistence.Column;
import lombok.Data;

/**
 * @Author: chasemoon
 * @CreateTime: 2025-02-23
 * @Description:
 * @Version:
 */

@Data
public class OrderProduct {

    private String productName;

    private float price;

    private String description;

    private String picture;

    private int quantity;
}
