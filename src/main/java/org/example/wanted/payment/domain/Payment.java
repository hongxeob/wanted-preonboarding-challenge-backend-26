package org.example.wanted.payment.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.wanted.base.BaseTimeEntity;
import org.example.wanted.order.domain.Order;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Payment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private String cancelReason = null;
    private LocalDateTime paidAt = null;
    private LocalDateTime cancelledAt = null;

    @Builder
    public Payment(Order order, PaymentMethod paymentMethod) {
        this.order = order;
        this.paymentMethod = paymentMethod;
        this.status = PaymentStatus.PENDING;
    }

    public void pay() {
        this.paidAt = LocalDateTime.now();
        this.status = PaymentStatus.COMPLETED;
    }

    public void cancel(String reason) {
        /**
         * 이미 취소상태인지 검증할 것인가?
         * */
        this.cancelReason = reason;
        this.cancelledAt = LocalDateTime.now();
        this.status = PaymentStatus.CANCELLED;
    }
}
