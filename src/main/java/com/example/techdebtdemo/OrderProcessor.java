package com.example.techdebtdemo;

import java.time.LocalDate;
import java.util.UUID;

public class OrderProcessor {
    public static double shippingRateOverride = 2.5;
    //Method to process an order - generating a UUID for that order and
    public void processOrder(Order order) {
        //validate the order
        if (order.getCustomerName() == null || order.getCustomerName().trim().isEmpty()) {
            throw new IllegalArgumentException("Order must have a customer name");
        }
        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one item");
        }
        //set the UUID
        order.setOrderId(UUID.randomUUID());
        System.out.println("Processing Order" + order.getOrderId()) ;
        //total price initialise
        double totalPrice = 0;
        //Apply the sale discount
        for (OrderItem item : order.getItems()) {
    double itemPrice = item.getPrice() * item.getQuantity();
    if (item.getProductCode().startsWith("SALE")) {
        itemPrice *= 0.9; // 10% discount on sale items
    } else {if (item.getQuantity() >= 3) {itemPrice *= 0.95; // 5% discount for 3 or more
        }
    }
    totalPrice += itemPrice;
        }
        if (totalPrice < 50) {
            totalPrice += shippingRateOverride;
            if(order.getOrderDate().isBefore(LocalDate.now().minusWeeks(2))) {
                totalPrice += totalPrice*1.25;
            }
        } else if (totalPrice >= 50 && totalPrice < 100) {
            totalPrice += shippingRateOverride / 2; // Using half the override
            if(order.getOrderDate().isBefore(LocalDate.now().minusWeeks(2))) {
                totalPrice += totalPrice*0.75;
            }
        }
        order.setTotalPrice(totalPrice);
        publishOrder(order);
    }
    //Process an order again
    public void reProcessOrder(Order order) {
        if (order.getCustomerName() == null || order.getCustomerName().trim().isEmpty()) {
            throw new IllegalArgumentException("Order must have a customer name");
        }
        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one item");
        }
        // Check for existing orderID
        if (order.getOrderId() == null) {
            throw new IllegalArgumentException("Reprocessed order id is null");
        }
        System.out.println("ReProcessing Order" + order.getOrderId()) ;
        double totalPrice = 0;
        for (OrderItem item : order.getItems()) {
            double itemPrice = item.getPrice() * item.getQuantity();
            if (item.getProductCode().startsWith("SALE")) {
                itemPrice *= 0.9; // 10% discount on sale items
            } else {
                if (item.getQuantity() >= 3) {
                    itemPrice *= 0.95; // 5% discount for 3 or more
                }
            }
            totalPrice += itemPrice;
        }
        // Apply shipping fee for small orders
        if (totalPrice < 50) {
            totalPrice += shippingRateOverride;
            if(order.getOrderDate().isBefore(LocalDate.now().minusWeeks(2))) {
                totalPrice += totalPrice*1.25;
            }
        } // Apply shipping fee for small orders
        else if (totalPrice >= 50 && totalPrice < 100) {
            totalPrice += shippingRateOverride / 2; // Using half the override
            if(order.getOrderDate().isBefore(LocalDate.now().minusWeeks(2))) {
                totalPrice += totalPrice*0.75;
            }
        }
        order.setTotalPrice(totalPrice);
        publishOrder(order);
    }
    public void publishOrder(Order order) {
        System.out.println("Publishing Order" + order.getOrderId()) ;
    }
}


