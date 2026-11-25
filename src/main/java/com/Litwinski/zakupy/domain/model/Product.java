package com.Litwinski.zakupy.domain.model;

import com.Litwinski.zakupy.domain.exception.InsufficientStockException;
import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private final UUID id;
    private final String name;
    private final BigDecimal price;
    private int stock;

    public Product(UUID id, String name, BigDecimal price, int stock) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nazwa produktu nie może być pusta");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Cena nie może być ujemna");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("Stan magazynowy nie może być ujemny");
        }
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public void validateStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Ilość musi być większa od zera");
        }
        if (this.stock < quantity) {
            throw new InsufficientStockException(name, stock, quantity);
        }
    }

    public void decreaseStock(int quantity) {
        validateStock(quantity);
        this.stock -= quantity;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public int getStock() { return stock; }
}