package org.example.wanted.user.application.dto;

import org.example.wanted.user.domain.User;

public record UserCreateRequest(
        String mail,
        String name,
        String phone) {
    public static User toEntity(UserCreateRequest request) {
        return User.builder()
                .email(request.mail)
                .name(request.name)
                .phone(request.phone)
                .build();
    }
}
