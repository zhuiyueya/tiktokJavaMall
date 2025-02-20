package com.chasemoon.gomall.controller;

import com.chasemoon.gomall.common.Result;
import com.chasemoon.gomall.pojo.dto.product.ListProductsRequest;
import com.chasemoon.gomall.pojo.dto.product.ListProductsResponse;
import com.chasemoon.gomall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping ("/list")
    public Result<ListProductsResponse> listProducts(@RequestBody ListProductsRequest listProductsRequest) {
        return Result.success(productService.ListProducts(listProductsRequest));
    }
}
