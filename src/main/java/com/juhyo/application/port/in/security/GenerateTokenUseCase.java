package com.juhyo.application.port.in.security;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

public interface GenerateTokenUseCase {
    TokenResponse generateToken(GenerateTokenCommand command);

    @Data
    @Builder
    class GenerateTokenCommand {
        private final String email;
        private final UUID userId;
    }

    @Data
    @Builder
    class TokenResponse {
        private final String accessToken;
        private final String refreshToken;
        private final long expiresIn;
    }
}
