package com.dio.budgetting.main.application.output;

import com.dio.budgetting.main.domain.User;

public record AuthUserOutput (String token, String id, String username) {
	public static AuthUserOutput from(User user, String token) {
        return new AuthUserOutput(
        				token,
        				user.getId().uuid().toString(),
        				user.getUsername()
		                );
    }
}