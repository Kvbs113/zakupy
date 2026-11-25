package com.Litwinski.zakupy.application.port.out;

import com.Litwinski.zakupy.domain.model.Order;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepositoryPort {
    Order save(Order order);
    Optional<Order> findById(UUID id);
}