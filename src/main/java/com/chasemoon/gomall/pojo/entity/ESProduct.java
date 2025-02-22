package com.chasemoon.gomall.pojo.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @Author: chasemoon
 * @CreateTime: 2025-02-21
 * @Description:针对ElasticSearchRepository的实体类
 * @Version:
 */


@Data
@Document(indexName = "products")
public class ESProduct {
    @Id
    private String esId;

    private int ProductId;
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String productName;

    private float price;

    private String description;

    private String picture;
}
