package org.example.wanted.user.application.impl;

import lombok.RequiredArgsConstructor;
import org.example.wanted.user.application.UserService;
import org.example.wanted.user.application.dto.UserCreateRequest;
import org.example.wanted.user.domain.User;
import org.example.wanted.user.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public String save(UserCreateRequest createRequest) {
        User entity = UserCreateRequest.toEntity(createRequest);
        User savedEntity = userRepository.save(entity);

        return savedEntity.getEmail();
    }
}
