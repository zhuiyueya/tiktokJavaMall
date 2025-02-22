package com.chasemoon.gomall.repository.es;

import com.chasemoon.gomall.pojo.entity.ESProduct;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @Author: chasemoon
 * @CreateTime: 2025-02-21
 * @Description:对接ElasticSearch存储，实现高性能搜索商品
 * @Version:
 */

public interface SearchProductRepository extends ElasticsearchRepository<ESProduct,String>{
    @Query("{\"match\": {\"productName\": \"?0\"}}")
    List<ESProduct> findByProductNameContaining(String productName);

    //List<Product> searchByProductNameAndPriceRange(String productName, float maxPrice, float minPrice);

}
