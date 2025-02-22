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
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SearchProductRepository searchProductRepository;

    public GetProductResponse getProduct(GetProductRequest request) {
        GetProductResponse response = new GetProductResponse();
        Product product=productRepository.findByProductId(request.getProductId());
        response.setProduct(product);
        return response;
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
        //java的列表存储的是product的地址，所以在for循环外new会导致最终List里的所有值都相同，都是同一个
        // 所有对象变量本质上是内存地址指针
        //Product product = new Product();
        for(ESProduct esp:esProducts){
            Product product = new Product();
            BeanUtils.copyProperties(esp,product);
            products.add(product);
        }
        return products;
    }

    public AddProductResponse addProduct(AddProductRequest addProductRequest) {
        productRepository.save(addProductRequest.getProduct());
        ESProduct esProduct=new ESProduct();
        log.info("addProduct== {}",addProductRequest.getProduct());
        BeanUtils.copyProperties(addProductRequest.getProduct(),esProduct);
        esProduct.setEsId(Integer.toString(addProductRequest.getProduct().getProductId()));
        log.info("esProduct== {}",esProduct);
        searchProductRepository.save(esProduct);

        AddProductResponse response = new AddProductResponse();
        response.setProduct(addProductRequest.getProduct());
        return response;
    }
}
