package com.dio.budgetting.main.infratructure.http;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dio.budgetting.main.application.AuthUserUseCase;
import com.dio.budgetting.main.application.RegisterUserUseCase;
import com.dio.budgetting.main.application.input.AuthUserInput;
import com.dio.budgetting.main.application.input.RegisterUserInput;
import com.dio.budgetting.main.infratructure.http.request.LoginRequest;
import com.dio.budgetting.main.infratructure.http.response.LoginResponse;
import com.dio.budgetting.main.infratructure.persistence.entity.Role;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RegisterUserUseCase registerUserUseCase;
    private final AuthUserUseCase authUserUseCase;
    
    public AuthController(RegisterUserUseCase registerUserUseCase, AuthUserUseCase authUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.authUserUseCase = authUserUseCase;
    }

    public static class RegisterRequest extends LoginRequest {
        private Set<String> roles;

        public Set<String> getRoles() { return roles; }
        public void setRoles(Set<String> roles) { this.roles = roles; }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequest request) {
        Set<Role> rolesEnum = request.getRoles() != null ?
                               request.getRoles().stream()
                                   .map(Role::valueOf)
                                   .collect(Collectors.toSet()) :
                               new HashSet<>();

        if (rolesEnum.isEmpty()) {
            rolesEnum.add(Role.USERS); 
        }

        registerUserUseCase.execute(new RegisterUserInput(request.getUsername(), request.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
    	var output = authUserUseCase.execute(new AuthUserInput(request.getUsername(), request.getPassword()));
    	return ResponseEntity.ok(LoginResponse.from(output));
    }
    
}