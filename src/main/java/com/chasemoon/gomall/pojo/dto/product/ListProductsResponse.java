package com.chasemoon.gomall.pojo.dto.product;

import com.chasemoon.gomall.pojo.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class ListProductsResponse {
    private List<Product> products;
}
