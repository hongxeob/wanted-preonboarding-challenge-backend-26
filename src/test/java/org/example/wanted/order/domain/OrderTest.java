package org.example.wanted.order.domain;

import org.example.wanted.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = genereateUser();
    }

    @Test
    @DisplayName("주문을 생성할 수 있다")
    void createOrderNumberSuccessTest() throws Exception {

        //given -> when
        Order order = generateOrder(user);

        //then
        assertThat(order).isNotNull();
        assertThat(order.getOrderNumber()).isNotNull();
        assertThat(order.getTotalAmount()).isEqualTo(BigDecimal.valueOf(100));
        assertThat(order.getStatus()).isEqualTo(OrderStatus.PENDING);
    }


    @Test
    @DisplayName("주문 상태를 변경할 수 있다")
    void updateStatusSuccessTest() throws Exception {

        //given
        Order order = generateOrder(user);
        OrderStatus requestStatus = OrderStatus.COMPLETED;
        //when
        order.updateStatus(requestStatus);

        //then
        assertThat(order.getStatus()).isEqualTo(requestStatus);
    }

    private static User genereateUser() {
        String email = "email@email.com";
        String name = "name";
        String phone = "010-1234-1234";

        return User.builder()
                .email(email)
                .name(name)
                .phone(phone)
                .build();
    }

    private static Order generateOrder(User user) {
        return Order.builder()
                .user(user)
                .totalAmount(BigDecimal.valueOf(100))
                .build();
    }
}
