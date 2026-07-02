package com.karachipizza.service;

import com.karachipizza.entity.Order;
import com.karachipizza.entity.OrderItem;
import org.springframework.stereotype.Service;

@Service
public class ReceiptService {
    
    public String generateReceipt(Order order) {
        StringBuilder receipt = new StringBuilder();
        receipt.append("=".repeat(40)).append("\n");
        receipt.append("  KARACHI PIZZA & FAST FOOD\n");
        receipt.append("  123 Food Street, Karachi\n");
        receipt.append("  Tel: +92 300 1234567\n");
        receipt.append("=".repeat(40)).append("\n");
        receipt.append("Order #: ").append(order.getOrderNumber()).append("\n");
        receipt.append("Date: ").append(order.getOrderDate()).append("\n");
        receipt.append("Cashier: ").append(order.getCashierName() != null ? order.getCashierName() : "Manager").append("\n");
        receipt.append("-".repeat(40)).append("\n");
        receipt.append("Items:\n");
        
        double subtotal = 0;
        for (OrderItem item : order.getItems()) {
            receipt.append(String.format("%-20s x%d  %8.2f\n", 
                    item.getItemName(), item.getQuantity(), item.getPrice() * item.getQuantity()));
            subtotal += item.getPrice() * item.getQuantity();
        }
        
        receipt.append("-".repeat(40)).append("\n");
        receipt.append(String.format("Subtotal: %28.2f\n", subtotal));
        receipt.append(String.format("Tax (10%%): %27.2f\n", order.getTax()));
        receipt.append(String.format("Discount: %28.2f\n", order.getDiscount()));
        receipt.append(String.format("Service: %27.2f\n", order.getServiceCharge()));
        receipt.append("=".repeat(40)).append("\n");
        receipt.append(String.format("TOTAL: %30.2f\n", order.getTotal()));
        receipt.append("=".repeat(40)).append("\n");
        receipt.append("Payment: ").append(order.getPaymentMethod()).append("\n");
        receipt.append("\nThank You! Visit Again!\n");
        receipt.append("=".repeat(40)).append("\n");
        
        return receipt.toString();
    }
}