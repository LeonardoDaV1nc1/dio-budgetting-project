package com.dio.budgetting.main.application.output;

import com.dio.budgetting.main.domain.User;

public record RegisterUserOutput (String id, String username) {
	public static RegisterUserOutput from(User user) {
        return new RegisterUserOutput(
        				user.getId().uuid().toString(),
        				user.getUsername()
		                );
    }
}