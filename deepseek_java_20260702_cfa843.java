package com.karachipizza.controller;

import com.karachipizza.entity.Order;
import com.karachipizza.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "${cors.allowed-origins}")
public class OrderController {
    
    private final OrderService orderService;
    
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        order.setOrderDate(LocalDateTime.now());
        return ResponseEntity.ok(orderService.createOrder(order));
    }
    
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable String id,
            @RequestParam Order.OrderStatus status) {
        return ResponseEntity.ok(orderService.updateOrderStatus(id, status));
    }
    
    @GetMapping("/today")
    public ResponseEntity<List<Order>> getTodayOrders() {
        return ResponseEntity.ok(orderService.getTodayOrders());
    }
    
    @GetMapping("/stats")
    public ResponseEntity<?> getOrderStats() {
        return ResponseEntity.ok(orderService.getOrderStats());
    }
}