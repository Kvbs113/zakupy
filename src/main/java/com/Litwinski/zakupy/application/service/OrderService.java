package com.Litwinski.zakupy.application.service;

import com.Litwinski.zakupy.application.port.in.PlaceOrderUseCase;
import com.Litwinski.zakupy.application.port.out.OrderRepositoryPort;
import com.Litwinski.zakupy.application.port.out.ProductRepositoryPort;
import com.Litwinski.zakupy.domain.exception.OrderNotFoundException;
import com.Litwinski.zakupy.domain.exception.ProductNotFoundException;
import com.Litwinski.zakupy.domain.model.Order;
import com.Litwinski.zakupy.domain.model.OrderItem;
import com.Litwinski.zakupy.domain.model.Product;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OrderService implements PlaceOrderUseCase {

    private final ProductRepositoryPort productRepository;
    private final OrderRepositoryPort orderRepository;

    public OrderService(ProductRepositoryPort productRepository,
                        OrderRepositoryPort orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order placeOrder(List<OrderItemCommand> commands) {
        if (commands == null || commands.isEmpty()) {
            throw new IllegalArgumentException("Lista produktów nie może być pusta");
        }

        // 1. Pobierz produkty i zsumuj ilości
        Map<Product, Integer> productsToUpdate = new LinkedHashMap<>();
        for (OrderItemCommand cmd : commands) {
            Product product = productRepository.findById(cmd.productId())
                    .orElseThrow(() -> new ProductNotFoundException(cmd.productId()));
            productsToUpdate.merge(product, cmd.quantity(), Integer::sum);
        }

        // 2. Sprawdź dostępność wszystkich produktów przed jakąkolwiek zmianą
        for (var entry : productsToUpdate.entrySet()) {
            entry.getKey().validateStock(entry.getValue());
        }

        // 3. Zmniejsz stany i zbuduj pozycje zamówienia
        List<OrderItem> orderItems = new ArrayList<>();
        for (var entry : productsToUpdate.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            product.decreaseStock(quantity);
            productRepository.save(product);

            orderItems.add(new OrderItem(
                    product.getId(),
                    product.getName(),
                    quantity,
                    product.getPrice()
            ));
        }

        Order order = Order.create(orderItems);
        return orderRepository.save(order);
    }

    @Override
    public Order getOrder(UUID orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }
}