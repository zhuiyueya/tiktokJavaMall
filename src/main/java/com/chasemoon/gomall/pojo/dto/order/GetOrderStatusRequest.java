package com.chasemoon.gomall.pojo.dto.order;

import com.chasemoon.gomall.pojo.entity.Order;
import lombok.Data;

/**
 * @Author: chasemoon
 * @CreateTime: 2025-02-23
 * @Description:
 * @Version:
 */
@Data
public class GetOrderStatusRequest {
    String orderId;
}
