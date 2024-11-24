package org.example.wanted.order.domain;

public enum OrderStatus {
    PENDING,    // 주문 생성
    PAID,       // 결제 완료
    CANCELLED,  // 주문 취소
    COMPLETED   // 주문 완료
}
