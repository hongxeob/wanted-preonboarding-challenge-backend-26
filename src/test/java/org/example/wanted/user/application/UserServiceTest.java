package org.example.wanted.user.application;

import org.example.wanted.user.application.dto.UserCreateRequest;
import org.example.wanted.user.application.impl.UserServiceImpl;
import org.example.wanted.user.domain.User;
import org.example.wanted.user.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;
    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .email("email@email.com")
                .name("name")
                .phone("010-1234-1234")
                .build();
    }

    @Test
    @DisplayName("유저를 생성 및 저장할 수 있다")
    void saveSuccessTest() throws Exception {

        //given
        UserCreateRequest createRequest = new UserCreateRequest("email@email.com", "name", "010-1234-1234");
        when(userRepository.save(any(User.class)))
                .thenReturn(user);
        //when
        String createdUserEmail = userService.save(createRequest);

        //then
        assertThat(createdUserEmail).isEqualTo(user.getEmail());
    }
}
