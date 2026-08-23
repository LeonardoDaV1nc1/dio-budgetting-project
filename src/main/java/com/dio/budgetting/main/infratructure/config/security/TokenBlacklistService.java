package com.dio.budgetting.main.infratructure.config.security;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class TokenBlacklistService {

    // Usaremos um HashSet em memória para armazenar tokens invalidados.
    // ATENÇÃO: Em produção, isso não é persistente e não escala em múltiplas instâncias.
    private final Set<String> blacklistedTokens = Collections.synchronizedSet(new HashSet<>());

    /**
     * Adiciona um token à blacklist em memória.
     * @param token O token JWT a ser invalidado.
     */
    public void blacklistToken(String token) {
        if (token != null && !token.isEmpty()) {
            blacklistedTokens.add(token);
            // Opcional: imprimir para depuração
            System.out.println("Token blacklisted (in-memory): " + token.substring(0, Math.min(token.length(), 20)) + "...");
        }
    }

    /**
     * Verifica se um token está na blacklist em memória.
     * @param token O token JWT a ser verificado.
     * @return true se o token estiver na blacklist, false caso contrário.
     */
    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }
}