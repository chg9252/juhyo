package com.jeoyo.application.port.out.security;

import java.util.Map;

public interface JwtPort {
    String generateAccessToken(String subject, Map<String, Object> claims);
    String generateRefreshToken(String subject);
    boolean validateToken(String token);
    String extractSubject(String token);
    Map<String, Object> extractClaims(String token);
}
