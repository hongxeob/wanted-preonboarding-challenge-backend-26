package org.example.wanted.order.application;

import org.example.wanted.order.domain.Order;
import org.example.wanted.user.domain.User;

import java.math.BigDecimal;

public record OrderCreateRequest(long userId, BigDecimal totalAmount) {
    public static Order toEntity(OrderCreateRequest createRequest, User user) {
        return Order.builder()
                .user(user)
                .totalAmount(createRequest.totalAmount())
                .build();
    }
}
