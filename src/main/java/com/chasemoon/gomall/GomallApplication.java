package com.chasemoon.gomall;

import com.chasemoon.gomall.pojo.entity.ESProduct;
import com.chasemoon.gomall.repository.es.SearchProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.chasemoon.gomall.repository.jpa")
@EnableElasticsearchRepositories(basePackages = "com.chasemoon.gomall.repository.es")
public class GomallApplication {

    public static void main(String[] args) {
        SpringApplication.run(GomallApplication.class, args);
    }
//
//    @Bean
//    @Profile("!prod") // 仅非生产环境生效
//    public CommandLineRunner demoDataInitializer(SearchProductRepository repo) {
//        return args -> {
//            // 示例一：电子产品
//            ESProduct phone = new ESProduct();
//            //phone.setId(1001);  // 测试环境预置ID（生产环境通常由DB自增）
//            phone.setProductName(" 小米");
//            phone.setPrice(6999.00f);  // 旗舰机型定价
//            phone.setDescription(" 搭载麒麟9000S芯片，支持卫星通信\nIP68防水防尘\n6.82英寸OLED曲面屏");
//            phone.setPicture("https://cdn.example.com/pics/huawei_mate60pro.jpg");
//            ESProduct snack = new ESProduct();
//            //snack.setId(1002);
//            snack.setProductName(" 大米"); // 未设置ID（依赖数据库自增）
//            snack.setPrice(79.90f);   // 促销价（原价99.9）
//            snack.setDescription(" 【今日秒杀】混合坚果礼包，7种科学配比");
//
//            // 批量存储与验证
//            List<ESProduct> products = Arrays.asList(phone, snack);
//            repo.saveAll(products);
//        };
//    }
}
