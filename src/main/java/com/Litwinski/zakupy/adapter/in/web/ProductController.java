package com.Litwinski.zakupy.adapter.in.web;

import com.Litwinski.zakupy.adapter.in.web.dto.CreateProductRequest;
import com.Litwinski.zakupy.application.port.in.ProductUseCase;
import com.Litwinski.zakupy.domain.model.Product;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductUseCase productUseCase;

    public ProductController(ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody CreateProductRequest request) {
        return productUseCase.addProduct(request.name(), request.price(), request.stock());
    }

    @GetMapping
    public List<Product> getAll() {
        return productUseCase.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable UUID id) {
        return productUseCase.getProduct(id);
    }
}