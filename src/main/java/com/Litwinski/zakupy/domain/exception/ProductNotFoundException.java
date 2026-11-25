package com.Litwinski.zakupy.domain.exception;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(UUID id) {
        super("Nie znaleziono produktu o ID: " + id);
    }
}