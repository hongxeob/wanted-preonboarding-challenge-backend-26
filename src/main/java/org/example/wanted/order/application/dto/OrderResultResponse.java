package org.example.wanted.order.application.dto;

import org.example.wanted.order.domain.Order;

import java.math.BigDecimal;

public record OrderResultResponse(
        Long orderId,
        BigDecimal totalAmount
) {
    public static OrderResultResponse from(Order order) {
        return new OrderResultResponse(order.getId(), order.getTotalAmount());
    }
}
