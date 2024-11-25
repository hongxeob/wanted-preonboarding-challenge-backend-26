package org.example.wanted.order.domain;

import org.example.wanted.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderItemTest {
    private Order order;
    private User user;

    @BeforeEach
    void setUp() {
        user = genereateUser();
        order = genereateOrder(user);
    }

    @Test
    @DisplayName("주문 아이템을 생성할 수 있다")
    void createSuccessTest() throws Exception {

        //given
        String itemName = "itemName";
        BigDecimal price = BigDecimal.valueOf(100);
        int quantity = 10;
        OrderItem orderItem = OrderItem.builder()
                .order(order)
                .itemName(itemName)
                .price(price)
                .quantity(quantity)
                .build();

        //then
        assertThat(orderItem).isNotNull();
        assertThat(orderItem.getOrder()).isEqualTo(order);
        assertThat(orderItem.getItemName()).isEqualTo(itemName);
        assertThat(orderItem.getPrice()).isEqualTo(price);
        assertThat(orderItem.getQuantity()).isEqualTo(quantity);
    }

    @Test
    @DisplayName("주문 아이템의 총 가격을 계산할 수 있다")
    void getTotalPriceSuccessTest() throws Exception {

        //given
        OrderItem orderItem = generateOrderItem(order);

        //when
        BigDecimal totalPrice = orderItem.getTotalPrice();

        //then
        assertThat(totalPrice).isNotNull();
        assertThat(totalPrice).isEqualTo(orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())));
        assertThat(totalPrice).isEqualTo(BigDecimal.valueOf(1000));

    }

    private static OrderItem generateOrderItem(Order order) {
        String itemName = "itemName";
        BigDecimal price = BigDecimal.valueOf(100);
        int quantity = 10;

        return OrderItem.builder()
                .order(order)
                .itemName(itemName)
                .price(price)
                .quantity(quantity)
                .build();
    }

    private static Order genereateOrder(User user) {
        return Order.builder()
                .user(user)
                .totalAmount(BigDecimal.valueOf(100))
                .build();
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
}
