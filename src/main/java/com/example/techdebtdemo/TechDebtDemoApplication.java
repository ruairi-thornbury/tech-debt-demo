package com.example.techdebtdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    }

}
