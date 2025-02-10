package com.chasemoon.gomall.pojo.dto.payment;

import com.chasemoon.gomall.pojo.entity.CreditCardInfo;

public class ChargeRequest {
    private float amount;
    private CreditCardInfo creditCard;
    private String orderId;
    private int userId;
}
