package com.chasemoon.gomall.repository.jpa;

import com.chasemoon.gomall.pojo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
