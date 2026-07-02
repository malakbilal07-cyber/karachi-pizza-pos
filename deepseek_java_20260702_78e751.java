package com.karachipizza.service;

import com.karachipizza.entity.Order;
import com.karachipizza.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final ReceiptService receiptService;
    
    @Transactional
    public Order createOrder(Order order) {
        order.setOrderNumber(generateOrderNumber());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Order.OrderStatus.PENDING);
        
        // Generate receipt
        String receipt = receiptService.generateReceipt(order);
        order.setReceiptText(receipt);
        
        return orderRepository.save(order);
    }
    
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    
    public Order getOrderById(String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
    
    @Transactional
    public Order updateOrderStatus(String id, Order.OrderStatus status) {
        Order order = getOrderById(id);
        order.setStatus(status);
        return orderRepository.save(order);
    }
    
    public List<Order> getTodayOrders() {
        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.atTime(LocalTime.MAX);
        return orderRepository.findByOrderDateBetween(start, end);
    }
    
    public Map<String, Object> getOrderStats() {
        List<Order> todayOrders = getTodayOrders();
        List<Order> allOrders = getAllOrders();
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("todayOrders", todayOrders.size());
        stats.put("todayRevenue", todayOrders.stream().mapToDouble(Order::getTotal).sum());
        stats.put("totalOrders", allOrders.size());
        stats.put("totalRevenue", allOrders.stream().mapToDouble(Order::getTotal).sum());
        
        // Status breakdown
        Map<String, Long> statusCounts = new HashMap<>();
        for (Order.OrderStatus status : Order.OrderStatus.values()) {
            long count = allOrders.stream()
                    .filter(o -> o.getStatus() == status)
                    .count();
            statusCounts.put(status.name(), count);
        }
        stats.put("statusCounts", statusCounts);
        
        return stats;
    }
    
    private String generateOrderNumber() {
        return "ORD" + System.currentTimeMillis() % 1000000;
    }
}