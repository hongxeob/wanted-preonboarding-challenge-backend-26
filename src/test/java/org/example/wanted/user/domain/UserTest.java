package org.example.wanted.user.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

	@Test
	@DisplayName("유저를 생성할 수 있다")
	void createSuccessTest() throws Exception {

		//given
		String email = "email@email.com";
		String name = "name";
		String phone = "010-1234-1234";

		//when
		User user = User.builder()
			.email(email)
			.name(name)
			.phone(phone)
			.build();

		//then
		assertThat(user).isNotNull();
		assertThat(user.getEmail()).isEqualTo(email);
		assertThat(user.getName()).isEqualTo(name);
		assertThat(user.getPhone()).isEqualTo(phone);
	}
}
