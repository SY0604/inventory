package com.example.inventorymanagerproj.errorhandlers;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String message) {
        super(message);
    }
} 