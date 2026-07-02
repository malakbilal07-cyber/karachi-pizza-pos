package com.karachipizza.controller;

import com.karachipizza.entity.MenuItem;
import com.karachipizza.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
@CrossOrigin(origins = "${cors.allowed-origins}")
public class MenuController {
    
    private final MenuService menuService;
    
    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        return ResponseEntity.ok(menuService.getAllMenuItems());
    }
    
    @GetMapping("/category/{category}")
    public ResponseEntity<List<MenuItem>> getMenuByCategory(@PathVariable String category) {
        return ResponseEntity.ok(menuService.getMenuByCategory(category));
    }
    
    @PostMapping
    public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItem menuItem) {
        return ResponseEntity.ok(menuService.createMenuItem(menuItem));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable Long id, @RequestBody MenuItem menuItem) {
        return ResponseEntity.ok(menuService.updateMenuItem(id, menuItem));
    }
}