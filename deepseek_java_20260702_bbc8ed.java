package com.karachipizza.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "menu_items")
@Data
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    private String category;
    private String size;
    private Double price;
    private Boolean isAvailable = true;
    private String description;
    private String imageUrl;
}