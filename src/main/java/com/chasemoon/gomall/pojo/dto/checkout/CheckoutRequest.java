package com.chasemoon.gomall.pojo.dto.checkout;

import com.chasemoon.gomall.pojo.entity.Address;
import com.chasemoon.gomall.pojo.entity.CreditCardInfo;

public class CheckoutRequest {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private CreditCardInfo creditCard;
}
