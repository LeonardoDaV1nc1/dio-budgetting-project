package com.dio.budgetting.main.infratructure.http.response;

import com.dio.budgetting.main.application.output.AuthUserOutput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private String id;
    private String username;
    
	public static LoginResponse from(AuthUserOutput output) {
		return new LoginResponse(output.token(), output.id(), output.username());
	}
}