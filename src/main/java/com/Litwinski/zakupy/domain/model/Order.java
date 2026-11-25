package com.Litwinski.zakupy.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record Order(UUID id, List<OrderItem> items, BigDecimal totalAmount, Instant createdAt) {

    public static Order create(List<OrderItem> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Zamówienie musi zawierać co najmniej jeden produkt");
        }
        BigDecimal total = items.stream()
                .map(OrderItem::lineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new Order(UUID.randomUUID(), List.copyOf(items), total, Instant.now());
    }
}