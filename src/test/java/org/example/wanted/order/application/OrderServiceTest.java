package org.example.wanted.order.application;

import org.example.wanted.exception.EntityNotFoundException;
import org.example.wanted.order.application.dto.OrderResultResponse;
import org.example.wanted.order.domain.Order;
import org.example.wanted.order.domain.OrderRepository;
import org.example.wanted.user.domain.User;
import org.example.wanted.user.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private User user;
    private Order order;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .email("email@email.com")
                .name("name")
                .phone("010-1234-1234")
                .build();
        order = Order.builder()
                .user(user)
                .totalAmount(BigDecimal.valueOf(100))
                .build();
    }

    @Test
    @DisplayName("결제를 위한 주문 자동 생성이 가능하다")
    void createOrderForPaymentSuccessTest() throws Exception {

        //given
        OrderCreateRequest createRequest = new OrderCreateRequest(1L, BigDecimal.valueOf(1000));
        when(userRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(user));
        when(orderRepository.save(any(Order.class)))
                .thenReturn(order);
        //when
        OrderResultResponse response = orderService.order(createRequest);

        //then
        assertThat(response).isNotNull();
        assertThat(response.totalAmount()).isEqualTo(BigDecimal.valueOf(100));
    }

    @Test
    @DisplayName("존재하지 않는 유저가 주문 생성시 예외 발생")
    void createOrderForPaymentFailTest() throws Exception {

        //given
        OrderCreateRequest createRequest = new OrderCreateRequest(1L, BigDecimal.valueOf(1000));
        when(userRepository.findById(any(Long.class)))
                .thenReturn(Optional.empty());
        //when -> then

        assertThrows(EntityNotFoundException.class,
                () -> orderService.order(createRequest));
    }
}
