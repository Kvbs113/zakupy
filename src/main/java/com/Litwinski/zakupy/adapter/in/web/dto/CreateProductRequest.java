package com.Litwinski.zakupy.adapter.in.web.dto;

import java.math.BigDecimal;

public record CreateProductRequest(String name, BigDecimal price, int stock) {}