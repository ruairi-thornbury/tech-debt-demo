package com.example.techdebtdemo;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class OrderProcessorTest {

    @Test
    void testProcessOrderWithValidInput() {
        OrderProcessor processor = new OrderProcessor();
        Order order = createTestOrder("John Doe", 2, 15.0, "Regular");

        processor.processOrder(order);

        // Assertions
        assertNotNull(order.getOrderId()); // Order ID should be generated
        assertEquals(32.5, order.getTotalPrice()); // Expected total price
    }

    @Test
    void testProcessOrderWithSaleItem() {
        OrderProcessor processor = new OrderProcessor();
        Order order = createTestOrder("Jane Smith", 1, 20.0, "SALE");

        processor.processOrder(order);

        assertEquals(20.5, order.getTotalPrice()); // Sale price + shipping
    }


    @Test
    void testProcessOrderWithInvalidCustomerName() {
        OrderProcessor processor = new OrderProcessor();
        Order order = createTestOrder("", 1, 10.0, "Regular");

        assertThrows(IllegalArgumentException.class, () -> processor.processOrder(order));
    }

    @Test
    void testProcessOrderWithEmptyItemList() {
        OrderProcessor processor = new OrderProcessor();
        Order order = new Order();
        order.setCustomerName("John Doe");
        order.setItems(new ArrayList<>()); // Empty item list

        assertThrows(IllegalArgumentException.class, () -> processor.processOrder(order));
    }

    @Test
    void testReprocessOrder(){
        OrderProcessor processor = new OrderProcessor();
        Order order = createTestOrder("John Doe", 2, 15.0, "Regular");
        processor.processOrder(order);
        double originalPrice = order.getTotalPrice();
        processor.reProcessOrder(order);
        assertEquals(originalPrice, order.getTotalPrice());
    }

    // Helper method to create test orders
    private Order createTestOrder(String customerName, int quantity, double price, String productCode) {
        Order order = new Order();
        order.setCustomerName(customerName);
        order.setOrderDate(LocalDate.now());

        OrderItem item = new OrderItem();
        item.setProductCode(productCode);
        item.setQuantity(quantity);
        item.setPrice(price);

        List<OrderItem> items = new ArrayList<>();
        items.add(item);
        order.setItems(items);

        return order;
    }
}
