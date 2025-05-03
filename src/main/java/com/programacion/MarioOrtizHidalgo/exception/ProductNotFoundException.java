package com.programacion.MarioOrtizHidalgo.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(Long id) {
        super("No existe un producto con el ID: %d".formatted(id));
    }

    public ProductNotFoundException() {
        super("No existen productos creados.");
    }
}