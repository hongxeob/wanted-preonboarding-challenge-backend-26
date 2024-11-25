package org.example.wanted.order.application;

import org.example.wanted.order.application.dto.OrderResultResponse;

public interface OrderService {
    OrderResultResponse order(OrderCreateRequest createRequest);
}
