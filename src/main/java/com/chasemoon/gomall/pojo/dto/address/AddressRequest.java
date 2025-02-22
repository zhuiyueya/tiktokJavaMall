package com.chasemoon.gomall.pojo.dto.address;

import lombok.Data;

/**
 * @Author: chasemoon
 * @CreateTime: 2025-02-22
 * @Description:
 * @Version:
 */
@Data
public class AddressRequest {
    private String streetAddress;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
