package com.juhyo.adapter.in.web.security;

import com.juhyo.adapter.in.web.dto.response.ApiResponse;
import com.juhyo.adapter.out.persistence.security.RefreshTokenEntity;
import com.juhyo.application.port.in.security.GenerateTokenUseCase;
import com.juhyo.application.port.in.security.ValidateTokenUseCase;
import com.juhyo.application.service.security.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RefreshTokenService refreshTokenService;
    private final ValidateTokenUseCase validateTokenUseCase;
    private final GenerateTokenUseCase generateTokenUseCase;

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<RefreshTokenResponse>> refreshToken(@RequestBody RefreshTokenRequest request) {
        return refreshTokenService.findByToken(request.refreshToken())
                .filter(refreshTokenService::verifyExpiration)
                .map(RefreshTokenEntity::getUserId)
                .map(userId -> {
                    // 토큰으로부터 이메일 정보 가져오기
                    ValidateTokenUseCase.TokenClaims claims = validateTokenUseCase.getTokenClaims(request.refreshToken());
                    
                    // 새 토큰 생성
                    GenerateTokenUseCase.GenerateTokenCommand command = GenerateTokenUseCase.GenerateTokenCommand.builder()
                            .email(claims.getEmail())
                            .userId(userId)
                            .build();
                    
                    GenerateTokenUseCase.TokenResponse tokenResponse = generateTokenUseCase.generateToken(command);
                    
                    // 새 리프레시 토큰 저장
                    refreshTokenService.createRefreshToken(userId, tokenResponse.getRefreshToken());

                    RefreshTokenResponse response = new RefreshTokenResponse(
                            tokenResponse.getAccessToken(),
                            tokenResponse.getRefreshToken(),
                            tokenResponse.getExpiresIn());

                    return ResponseEntity.ok(ApiResponse.success("토큰이 성공적으로 갱신되었습니다.", response));
                })
                .orElse(ResponseEntity.badRequest().body(ApiResponse.error("리프레시 토큰이 유효하지 않거나 만료되었습니다.")));
    }
    
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody LogoutRequest request) {
        refreshTokenService.deleteByUserId(request.userId());
        return ResponseEntity.ok("Logged out successfully");
    }
    
    // DTO 클래스
    public record RefreshTokenRequest(String refreshToken) {}
    public record RefreshTokenResponse(String accessToken, String refreshToken, long expiresIn) {}
    public record LogoutRequest(UUID userId) {}
}