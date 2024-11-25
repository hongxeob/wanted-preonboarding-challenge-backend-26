package org.example.wanted.payment;

import org.example.wanted.order.domain.Order;
import org.example.wanted.payment.domain.Payment;
import org.example.wanted.payment.domain.PaymentMethod;
import org.example.wanted.payment.domain.PaymentStatus;
import org.example.wanted.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentTest {

    private Order order;
    private User user;

    @BeforeEach
    void setUp() {
        user = genereateUser();
        order = genereateOrder(user);
    }

    @Test
    @DisplayName("결제를 생성할 수 있다")
    void createPaymentSuccessTest() throws Exception {

        //given -> when
        Payment payment = genereatePayment(order);

        //then
        assertThat(payment).isNotNull();
        assertThat(payment.getPaymentMethod()).isEqualTo(PaymentMethod.CARD);
        assertThat(payment.getOrder()).isEqualTo(order);
        assertThat(payment.getCancelReason()).isNull();
    }

    @Test
    @DisplayName("결제를 지불할 수 있다")
    void paySuccessTest() throws Exception {

        //given
        Payment payment = genereatePayment(order);

        //when
        payment.pay();

        //then
        assertThat(payment.getStatus()).isEqualTo(PaymentStatus.COMPLETED);
        assertThat(payment.getPaidAt()).isNotNull();
    }

    @Test
    @DisplayName("결제를 취소할 수 있다")
    void cancelSuccessTest() throws Exception {

        //given
        Payment payment = genereatePayment(order);

        //when
        payment.cancel("reason");

        //then
        assertThat(payment.getCancelReason()).isEqualTo("reason");
        assertThat(payment.getCancelledAt()).isNotNull();
        assertThat(payment.getStatus()).isEqualTo(PaymentStatus.CANCELLED);
    }

    private static Payment genereatePayment(Order order) {
        return Payment.builder()
                .order(order)
                .paymentMethod(PaymentMethod.CARD)
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
