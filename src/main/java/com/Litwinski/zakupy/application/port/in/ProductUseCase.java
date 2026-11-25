package com.Litwinski.zakupy.application.port.in;

import com.Litwinski.zakupy.domain.model.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ProductUseCase {
    Product addProduct(String name, BigDecimal price, int stock);
    List<Product> getAllProducts();
    Product getProduct(UUID id);
}