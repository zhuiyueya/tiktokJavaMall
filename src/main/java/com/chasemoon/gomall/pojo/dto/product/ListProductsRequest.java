package com.chasemoon.gomall.pojo.dto.product;

import lombok.Data;

@Data
public class ListProductsRequest
{
    private int page;
    private int pageSize;
    private String categoryName;
}
