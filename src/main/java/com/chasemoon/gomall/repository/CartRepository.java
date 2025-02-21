package com.chasemoon.gomall.repository;

import com.chasemoon.gomall.pojo.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {

    Cart findByUserId(int userId);
}
