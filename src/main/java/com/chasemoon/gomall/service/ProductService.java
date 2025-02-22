package com.chasemoon.gomall.service;

import com.chasemoon.gomall.pojo.dto.product.*;
import com.chasemoon.gomall.pojo.entity.ESProduct;
import com.chasemoon.gomall.pojo.entity.Product;
import com.chasemoon.gomall.repository.jpa.ProductRepository;
import com.chasemoon.gomall.repository.es.SearchProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SearchProductRepository searchProductRepository;

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
        SearchProductRespnse response = new SearchProductRespnse();
        log.info("query== {}",request.getQuery());
        List<ESProduct>esProducts=searchProductRepository.findByProductNameContaining(request.getQuery());
        //log.info("esProducts== {}",esProducts);
        response.setProducts(copyEsProductListToProductList(esProducts));
        log.info("Products== {}",response.getProducts());
        return response;
    }
    public List<Product> copyEsProductListToProductList(List<ESProduct> esProducts) {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        for(ESProduct esp:esProducts){
            BeanUtils.copyProperties(esp,product);
            products.add(product);
        }
        return products;
    }

}
