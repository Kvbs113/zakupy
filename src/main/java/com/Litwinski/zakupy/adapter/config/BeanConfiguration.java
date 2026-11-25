package com.Litwinski.zakupy.adapter.config;

import com.Litwinski.zakupy.adapter.out.persistence.InMemoryOrderRepository;
import com.Litwinski.zakupy.adapter.out.persistence.InMemoryProductRepository;
import com.Litwinski.zakupy.application.port.in.PlaceOrderUseCase;
import com.Litwinski.zakupy.application.port.in.ProductUseCase;
import com.Litwinski.zakupy.application.port.out.OrderRepositoryPort;
import com.Litwinski.zakupy.application.port.out.ProductRepositoryPort;
import com.Litwinski.zakupy.application.service.OrderService;
import com.Litwinski.zakupy.application.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ProductRepositoryPort productRepositoryPort() {
        return new InMemoryProductRepository();
    }

    @Bean
    public OrderRepositoryPort orderRepositoryPort() {
        return new InMemoryOrderRepository();
    }

    @Bean
    public ProductUseCase productUseCase(ProductRepositoryPort productRepositoryPort) {
        return new ProductService(productRepositoryPort);
    }

    @Bean
    public PlaceOrderUseCase placeOrderUseCase(ProductRepositoryPort productRepositoryPort,
                                               OrderRepositoryPort orderRepositoryPort) {
        return new OrderService(productRepositoryPort, orderRepositoryPort);
    }
}