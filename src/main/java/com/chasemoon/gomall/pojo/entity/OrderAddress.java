package com.chasemoon.gomall.pojo.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

/**
 * @Author: chasemoon
 * @CreateTime: 2025-02-22
 * @Description:
 * @Version:
 */
@Embeddable
@Data
public class OrderAddress {
    private String streetAddress;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
