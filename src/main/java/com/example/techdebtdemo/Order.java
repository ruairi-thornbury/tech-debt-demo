package com.example.techdebtdemo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class Order {
    private String customerName;
    private List<OrderItem> items;
    private double totalPrice;
    private LocalDate orderDate;
    private UUID orderId;


    public void validate() {
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Order must have a customer name");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one item");
        }
    }
}