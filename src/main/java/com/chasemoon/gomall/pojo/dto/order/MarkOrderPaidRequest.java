package com.chasemoon.gomall.pojo.dto.order;

import lombok.Data;

@Data
public class MarkOrderPaidRequest {
    private int userId;
    private String orderId;
}
