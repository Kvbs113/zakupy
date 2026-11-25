package com.Litwinski.zakupy.adapter.out.persistence;

import com.Litwinski.zakupy.application.port.out.ProductRepositoryPort;
import com.Litwinski.zakupy.domain.model.Product;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryProductRepository implements ProductRepositoryPort {

    private final Map<UUID, Product> storage = new ConcurrentHashMap<>();

    @Override
    public Product save(Product product) {
        storage.put(product.getId(), product);
        return product;
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Product> findAll() {
        return List.copyOf(storage.values());
    }
}