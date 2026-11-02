package com.example.onlinefoodordersystem.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.example.onlinefoodordersystem.model.Order;
import com.example.onlinefoodordersystem.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService service;


    // 1. Get All Orders

    @GetMapping
    public List<Order> getAllOrders() {

        return service.getAllOrders();
    }


    // 2. Get Order By ID

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(
            @PathVariable Long id) {

        Order order = service.getOrderById(id);

        if (order == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(order);
    }


    // 3. Add New Order

    @PostMapping
    public ResponseEntity<Order> addOrder(
            @Valid @RequestBody Order order) {

        Order savedOrder = service.addOrder(order);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedOrder);
    }


    // 4. Update Complete Order

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(
            @PathVariable Long id,
            @Valid @RequestBody Order order) {

        Order updatedOrder = service.updateOrder(id, order);

        if (updatedOrder == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedOrder);
    }


    // 5. Delete Order

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(
            @PathVariable Long id) {

        boolean deleted = service.deleteOrder(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Order deleted successfully");
    }


    // 6. Partial Update

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchOrder(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        try {

            Order updatedOrder = service.patchOrder(id, updates);

            if (updatedOrder == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(updatedOrder);

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    // 7. Get Orders By Customer Name

    @GetMapping("/customer/{name}")
    public List<Order> getOrdersByCustomerName(
            @PathVariable String name) {

        return service.getOrdersByCustomerName(name);
    }


    // 8. Get Orders By Food Item

    @GetMapping("/food/{food}")
    public List<Order> getOrdersByFoodItem(
            @PathVariable String food) {

        return service.getOrdersByFoodItem(food);
    }

}