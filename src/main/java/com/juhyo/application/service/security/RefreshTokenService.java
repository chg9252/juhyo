package com.juhyo.application.service.security;

import com.juhyo.adapter.out.persistence.security.RefreshTokenEntity;
import com.juhyo.adapter.out.persistence.security.RefreshTokenRepository;
import com.juhyo.adapter.out.persistence.user.UserJpaEntity;
import com.juhyo.adapter.out.persistence.user.UserRepository;
import com.juhyo.config.JwtProperties;
import jakarta.persistence.EntityNotFoundException;
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
    private final UserRepository userRepository;

    @Transactional
    public RefreshTokenEntity createRefreshToken(UUID userId, String token) {

        UserJpaEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));


        RefreshTokenEntity refreshToken = RefreshTokenEntity.builder()
                .user(user)
                .token(token)
                .expiryDate(Instant.now().plusMillis(jwtProperties.getRefreshTokenValidityInMilliseconds()))
                .build();

        // 기존 토큰이 있다면 삭제
        refreshTokenRepository.findByUser_Id(userId).ifPresent(entity ->
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
        refreshTokenRepository.deleteByUser_Id(userId);
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