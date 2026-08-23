package com.dio.budgetting.main.application;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dio.budgetting.main.application.input.AuthUserInput;
import com.dio.budgetting.main.application.output.AuthUserOutput;
import com.dio.budgetting.main.domain.UserRepository;
import com.dio.budgetting.main.infratructure.config.security.JwtService;

@Service
public class AuthUserUseCase {
	
	private UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
	
	public AuthUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
		this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
	}
	
	public AuthUserOutput execute(AuthUserInput input) {
		
		try {
            Authentication authentication = authenticationManager.authenticate(
            		new UsernamePasswordAuthenticationToken(input.username(), input.password())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtService.generateToken(userDetails);
            
            var user = userRepository.findByUsername(input.username());
            return AuthUserOutput.from(user, token);
        } catch (BadCredentialsException e) {
            throw new IllegalArgumentException("Credenciais inválidas: username ou senha incorretos.");
        }
		
	}

}