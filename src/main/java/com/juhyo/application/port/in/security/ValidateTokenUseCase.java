package com.juhyo.application.port.in.security;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

public interface ValidateTokenUseCase {
    boolean validateToken(String token);
    
    TokenClaims getTokenClaims(String token);
    
    @Data
    @Builder
    class TokenClaims {
        private final String email;
        private final UUID userId;
    }
}
