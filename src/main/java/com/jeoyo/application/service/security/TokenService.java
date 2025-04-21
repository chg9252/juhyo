package com.jeoyo.application.service.security;

import com.jeoyo.application.port.in.security.GenerateTokenUseCase;
import com.jeoyo.application.port.in.security.ValidateTokenUseCase;
import com.jeoyo.application.port.out.security.JwtPort;
import com.jeoyo.config.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService implements GenerateTokenUseCase, ValidateTokenUseCase {

    private final JwtPort jwtPort;
    private final JwtProperties jwtProperties;

    @Override
    public TokenResponse generateToken(GenerateTokenCommand command) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", command.getUserId());

        String accessToken = jwtPort.generateAccessToken(command.getEmail(), claims);
        String refreshToken = jwtPort.generateRefreshToken(command.getEmail());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiresIn(jwtProperties.getAccessTokenValidityInMilliseconds() / 1000)
                .build();
    }

    @Override
    public boolean validateToken(String token) {
        return jwtPort.validateToken(token);
    }

    @Override
    public TokenClaims getTokenClaims(String token) {
        Map<String, Object> claims = jwtPort.extractClaims(token);
        String email = jwtPort.extractSubject(token);
        UUID userId = UUID.fromString((String) claims.get("userId"));

        return TokenClaims.builder()
                .email(email)
                .userId(userId)
                .build();
    }
}
