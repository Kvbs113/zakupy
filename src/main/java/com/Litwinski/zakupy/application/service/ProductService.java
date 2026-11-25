package com.Litwinski.zakupy.application.service;

import com.Litwinski.zakupy.application.port.in.ProductUseCase;
import com.Litwinski.zakupy.application.port.out.ProductRepositoryPort;
import com.Litwinski.zakupy.domain.exception.ProductNotFoundException;
import com.Litwinski.zakupy.domain.model.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ProductService implements ProductUseCase {

    private final ProductRepositoryPort productRepository;

    public ProductService(ProductRepositoryPort productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(String name, BigDecimal price, int stock) {
        Product product = new Product(UUID.randomUUID(), name, price, stock);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}