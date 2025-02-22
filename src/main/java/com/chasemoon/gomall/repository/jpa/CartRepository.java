package com.chasemoon.gomall.repository.jpa;

import com.chasemoon.gomall.pojo.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {

    Cart findByUserId(int userId);
}
