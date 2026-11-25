package com.Litwinski.zakupy.adapter.in.web.dto;

import java.util.List;
import java.util.UUID;

public record PlaceOrderRequest(List<ItemDto> items) {
    public record ItemDto(UUID productId, int quantity) {}
}