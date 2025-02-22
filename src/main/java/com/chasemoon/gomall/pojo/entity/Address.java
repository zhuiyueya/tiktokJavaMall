package com.chasemoon.gomall.pojo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="address_id")
    private Long addressId;
    @Column(name="user_id")
    private int userId;
    @Column(name="street_address")
    private String streetAddress;
    private String city;
    private String state;
    private String country;
    @Column(name="zip_code")
    private String zipCode;
}
