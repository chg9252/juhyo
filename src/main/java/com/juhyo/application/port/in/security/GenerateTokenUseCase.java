package com.juhyo.application.port.in.security;

import lombok.Builder;
import lombok.Data;

public interface GenerateTokenUseCase {
    TokenResponse generateToken(GenerateTokenCommand command);

    @Data
    @Builder
    class GenerateTokenCommand {
        private final String email;
        private final Long userId;
    }

    @Data
    @Builder
    class TokenResponse {
        private final String accessToken;
        private final String refreshToken;
        private final long expiresIn;
    }
}
