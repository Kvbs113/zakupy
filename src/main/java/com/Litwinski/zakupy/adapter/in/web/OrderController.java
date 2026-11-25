package com.Litwinski.zakupy.adapter.in.web;

import com.Litwinski.zakupy.adapter.in.web.dto.PlaceOrderRequest;
import com.Litwinski.zakupy.application.port.in.PlaceOrderUseCase;
import com.Litwinski.zakupy.application.port.in.PlaceOrderUseCase.OrderItemCommand;
import com.Litwinski.zakupy.domain.model.Order;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final PlaceOrderUseCase placeOrderUseCase;

    public OrderController(PlaceOrderUseCase placeOrderUseCase) {
        this.placeOrderUseCase = placeOrderUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order placeOrder(@RequestBody PlaceOrderRequest request) {
        List<OrderItemCommand> commands = request.items().stream()
                .map(i -> new OrderItemCommand(i.productId(), i.quantity()))
                .toList();
        return placeOrderUseCase.placeOrder(commands);
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable UUID id) {
        return placeOrderUseCase.getOrder(id);
    }
}