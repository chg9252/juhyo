package com.juhyo.application.service.security;

import com.juhyo.adapter.out.persistence.security.RefreshTokenEntity;
import com.juhyo.adapter.out.persistence.security.RefreshTokenRepository;
import com.juhyo.config.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProperties jwtProperties;

    @Transactional
    public RefreshTokenEntity createRefreshToken(UUID userId, String token) {
        RefreshTokenEntity refreshToken = RefreshTokenEntity.builder()
                .userId(userId)
                .token(token)
                .expiryDate(Instant.now().plusMillis(jwtProperties.getRefreshTokenValidityInMilliseconds()))
                .build();

        // 기존 토큰이 있다면 삭제
        refreshTokenRepository.findByUserId(userId).ifPresent(entity -> 
            refreshTokenRepository.deleteById(entity.getId())
        );

        return refreshTokenRepository.save(refreshToken);
    }

    @Transactional(readOnly = true)
    public Optional<RefreshTokenEntity> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Transactional
    public void deleteByUserId(UUID userId) {
        refreshTokenRepository.deleteByUserId(userId);
    }
    
    @Transactional
    public boolean verifyExpiration(RefreshTokenEntity token) {
        if (token.isExpired()) {
            refreshTokenRepository.delete(token);
            return false;
        }
        return true;
    }
}