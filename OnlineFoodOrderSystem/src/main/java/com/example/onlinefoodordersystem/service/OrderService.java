package com.example.onlinefoodordersystem.service;

import java.util.*;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.onlinefoodordersystem.model.Order;

@Service
public class OrderService {

    private List<Order> orders = new ArrayList<>();

    private AtomicLong idCounter = new AtomicLong(1);


    // Add Order

    public Order addOrder(Order order) {

        order.setOrderId(idCounter.getAndIncrement());

        orders.add(order);

        return order;
    }


    // Get All Orders

    public List<Order> getAllOrders() {

        return orders;
    }


    // Get Order By ID

    public Order getOrderById(Long id) {

        return orders.stream()
                .filter(o -> o.getOrderId().equals(id))
                .findFirst()
                .orElse(null);
    }


    // Update Complete Order

    public Order updateOrder(Long id, Order newOrder) {

        Order oldOrder = getOrderById(id);

        if (oldOrder == null) {
            return null;
        }

        oldOrder.setCustomerName(newOrder.getCustomerName());
        oldOrder.setFoodItem(newOrder.getFoodItem());
        oldOrder.setQuantity(newOrder.getQuantity());
        oldOrder.setPrice(newOrder.getPrice());
        oldOrder.setMobileNumber(newOrder.getMobileNumber());
        oldOrder.setDeliveryAddress(newOrder.getDeliveryAddress());

        return oldOrder;
    }


    // Delete Order

    public boolean deleteOrder(Long id) {

        return orders.removeIf(o -> o.getOrderId().equals(id));
    }


    // Search By Customer Name

    public List<Order> getOrdersByCustomerName(String name) {

        return orders.stream()
                .filter(o -> o.getCustomerName()
                .equalsIgnoreCase(name))
                .toList();
    }


    // Search By Food Item

    public List<Order> getOrdersByFoodItem(String food) {

        return orders.stream()
                .filter(o -> o.getFoodItem()
                .equalsIgnoreCase(food))
                .toList();
    }


    // Partial Update

    public Order patchOrder(Long id, Map<String, Object> updates) {

        Order order = getOrderById(id);

        if (order == null) {
            return null;
        }

        if (updates.isEmpty()) {
            throw new IllegalArgumentException("No fields provided");
        }

        for (String key : updates.keySet()) {

            Object value = updates.get(key);

            switch (key) {

                case "customerName":

                    order.setCustomerName(
                        requiredText(value, key));
                    break;

                case "foodItem":

                    order.setFoodItem(
                        requiredText(value, key));
                    break;

                case "quantity":

                    if (!(value instanceof Number)
                            || ((Number) value).intValue() < 1) {
                        throw new IllegalArgumentException(
                            "Quantity must be at least 1");
                    }

                    order.setQuantity(((Number) value).intValue());
                    break;

                case "price":

                    if (!(value instanceof Number)) {
                        throw new IllegalArgumentException(
                            "Price must be a number");
                    }

                    order.setPrice(((Number) value).doubleValue());
                    break;

                case "mobileNumber":

                    String mobile = requiredText(value, key);

                    if (!mobile.matches("[0-9]{10}")) {
                        throw new IllegalArgumentException(
                            "Mobile number must contain 10 digits");
                    }

                    order.setMobileNumber(mobile);
                    break;

                case "deliveryAddress":

                    order.setDeliveryAddress(
                        requiredText(value, key));
                    break;

                default:

                    throw new IllegalArgumentException(
                        "Invalid field: " + key);
            }
        }

        return order;
    }


    private String requiredText(Object value, String field) {

        if (!(value instanceof String)
                || ((String) value).isBlank()) {
            throw new IllegalArgumentException(
                field + " cannot be empty");
        }

        return (String) value;
    }
}