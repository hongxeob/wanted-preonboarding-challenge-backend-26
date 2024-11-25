package org.example.wanted.user.application;

import org.example.wanted.user.application.dto.UserCreateRequest;

public interface UserService {
    String save(UserCreateRequest createRequest);
}
