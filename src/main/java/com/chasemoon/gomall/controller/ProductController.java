package com.chasemoon.gomall.controller;

import com.chasemoon.gomall.common.Result;
import com.chasemoon.gomall.pojo.dto.product.*;
import com.chasemoon.gomall.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping ("/list")
    public Result<ListProductsResponse> listProducts(@RequestBody ListProductsRequest listProductsRequest) {
        return Result.success(productService.ListProducts(listProductsRequest));
    }
    @PostMapping("/search")
    public Result<SearchProductRespnse> searchProducts(@RequestBody SearchProductRequest searchProductRequest) {
        log.info("post serach {}", searchProductRequest);
        return Result.success(productService.SearchProduct(searchProductRequest));
    }
    @GetMapping("/get/{$productId}")
    public Result<GetProductResponse> getProduct(@PathVariable("$productId")String productId) {
        GetProductRequest getProductRequest = new GetProductRequest();
        getProductRequest.setProductId(Integer.parseInt(productId));
        return Result.success(productService.getProduct(getProductRequest));
    }

    @PostMapping("/add")
    public Result<AddProductResponse> addProduct(@RequestBody AddProductRequest addProductRequest) {
        return Result.success(productService.addProduct(addProductRequest));
    }
    
}
