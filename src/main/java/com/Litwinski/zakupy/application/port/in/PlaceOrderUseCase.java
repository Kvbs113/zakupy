package com.Litwinski.zakupy.application.port.in;

import com.Litwinski.zakupy.domain.model.Order;
import java.util.List;
import java.util.UUID;

public interface PlaceOrderUseCase {
    Order placeOrder(List<OrderItemCommand> items);
    Order getOrder(UUID orderId);

    record OrderItemCommand(UUID productId, int quantity) {}
}