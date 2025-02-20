package com.chasemoon.gomall.service;

import com.chasemoon.gomall.common.Constants;
import com.chasemoon.gomall.pojo.dto.product.*;
import com.chasemoon.gomall.pojo.entity.Product;
import com.chasemoon.gomall.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public GetProductResponse getProduct(GetProductRequest request) {
        return null;
    }
    public ListProductsResponse ListProducts(ListProductsRequest request) {
        ListProductsResponse response = new ListProductsResponse();

        List<Product> products = productRepository.findAll();

        response.setProducts(products);



        return response;
    }
    public SearchProductRespnse SearchProduct(SearchProductRequest request) {
        return null;
    }

}
