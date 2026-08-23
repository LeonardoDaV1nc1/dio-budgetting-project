package com.dio.budgetting.main.application;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dio.budgetting.main.application.input.RegisterUserInput;
import com.dio.budgetting.main.application.output.RegisterUserOutput;
import com.dio.budgetting.main.domain.User;
import com.dio.budgetting.main.domain.UserRepository;

@Service
public class RegisterUserUseCase {
	
	private UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public RegisterUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public RegisterUserOutput execute(RegisterUserInput input) {
		
        String encodedPassword = passwordEncoder.encode(input.password());
		
		var user = userRepository.save(
				new User(input.username(), encodedPassword));
		return RegisterUserOutput.from(user);
	}

}