package com.chasemoon.gomall.repository.jpa;

import com.chasemoon.gomall.pojo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductId(int productId);

    Product getProductByProductId(int productId);
}
