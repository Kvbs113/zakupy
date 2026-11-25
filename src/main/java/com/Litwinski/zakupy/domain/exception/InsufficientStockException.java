package com.Litwinski.zakupy.domain.exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String productName, int available, int requested) {
        super("Brak wystarczającej ilości produktu '%s' (dostępne: %d, żądane: %d)"
                .formatted(productName, available, requested));
    }
}