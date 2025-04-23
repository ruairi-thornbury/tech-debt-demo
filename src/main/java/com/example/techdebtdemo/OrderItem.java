package com.example.techdebtdemo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderItem {
    private String productCode;
    private int quantity;
    private double price;
}