package com.karachipizza.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @Column(nullable = false)
    private String orderNumber;
    
    @Column(nullable = false)
    private LocalDateTime orderDate;
    
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    
    @Enumerated(EnumType.STRING)
    private OrderType orderType;
    
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    
    @Column(nullable = false)
    private Double subtotal;
    
    private Double tax;
    private Double discount;
    private Double serviceCharge;
    
    @Column(nullable = false)
    private Double total;
    
    private String customerName;
    private String customerPhone;
    private String customerEmail;
    private String customerAddress;
    
    private String cashierName;
    private String notes;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> items;
    
    private String receiptText;
    
    public enum OrderStatus {
        PENDING, PREPARING, READY, COMPLETED, CANCELLED
    }
    
    public enum OrderType {
        DINE_IN, TAKE_AWAY, DELIVERY
    }
    
    public enum PaymentMethod {
        CASH, CARD, BANK_TRANSFER, MOBILE_WALLET
    }
}