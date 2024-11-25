package org.example.wanted.order.application.impl;

import lombok.RequiredArgsConstructor;
import org.example.wanted.exception.EntityNotFoundException;
import org.example.wanted.order.application.OrderService;
import org.example.wanted.order.application.dto.OrderCreateRequest;
import org.example.wanted.order.application.dto.OrderResultResponse;
import org.example.wanted.order.domain.Order;
import org.example.wanted.order.domain.OrderRepository;
import org.example.wanted.user.domain.User;
import org.example.wanted.user.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.example.wanted.util.enumeration.ErrorCode.NOT_FOUND_USER_BY_ID;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public OrderResultResponse order(OrderCreateRequest createRequest) {
        User user = userRepository.findById(createRequest.userId())
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_USER_BY_ID));

        Order order = OrderCreateRequest.toEntity(createRequest, user);
        Order savedOrder = orderRepository.save(order);

        return OrderResultResponse.from(savedOrder);
    }
}
