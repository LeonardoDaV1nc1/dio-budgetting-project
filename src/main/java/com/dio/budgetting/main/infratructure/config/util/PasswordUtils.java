package com.dio.budgetting.main.infratructure.config.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {
	
	public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "admin123"; // Use a senha exata que você quer para login
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Encoded password for '" + rawPassword + "': " + encodedPassword);
        // Saída será algo como: Encoded password for 'admin123': $2a$10$XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    }

}
