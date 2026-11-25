package com.Litwinski.zakupy.domain.exception;

import java.util.UUID;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(UUID id) {
        super("Nie znaleziono zamówienia o ID: " + id);
    }
}