package com.example.techdebtdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
public class TechDebtDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechDebtDemoApplication.class, args);
    }

    @RestController
    static class OrderController {

        private final OrderProcessor orderProcessor = new OrderProcessor();

        @PostMapping("/order")
        public void processOrder(@RequestBody Order order) {
            orderProcessor.processOrder(order);
            orderProcessor.publishOrder(order);
        }

        @PostMapping("/replay/order")
        public void reProcessOrder(@RequestBody Order order) {
            orderProcessor.reProcessOrder(order);
            orderProcessor.publishOrder(order);
        }

        @DeleteMapping ("/order")
        public void deleteOrder(@RequestBody Order order) {
            orderProcessor.deleteOrder(order);
        }
    }
}
