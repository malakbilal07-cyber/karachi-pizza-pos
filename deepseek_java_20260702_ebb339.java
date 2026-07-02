package com.karachipizza.repository;

import com.karachipizza.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByOrderDateBetween(LocalDateTime start, LocalDateTime end);
    List<Order> findByStatus(Order.OrderStatus status);
    long countByStatus(Order.OrderStatus status);
}