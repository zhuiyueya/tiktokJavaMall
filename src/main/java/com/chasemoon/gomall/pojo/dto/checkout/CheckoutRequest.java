package com.chasemoon.gomall.pojo.dto.checkout;

import com.chasemoon.gomall.pojo.entity.Address;
import com.chasemoon.gomall.pojo.entity.CreditCardInfo;
import lombok.Data;

@Data
public class CheckoutRequest {
    private int userId;

    //private CreditCardInfo creditCard;
}
