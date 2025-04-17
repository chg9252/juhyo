package com.juhyo.application.service.user;

import com.juhyo.application.port.in.security.GenerateTokenUseCase;
import com.juhyo.application.port.in.user.LoginUseCase;
import com.juhyo.application.port.in.user.RegisterUserUseCase;
import com.juhyo.application.port.out.user.UserPort;
import com.juhyo.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements RegisterUserUseCase, LoginUseCase {

    private final UserPort userPort;
    private final PasswordEncoder passwordEncoder;
    private final GenerateTokenUseCase generateTokenUseCase;

    @Override
    public User registerUser(RegisterUserCommand command) {
        // 중복 이메일 검사
        if (userPort.existsByEmail(command.getEmail())) {
            throw new IllegalArgumentException("이미 등록된 이메일 입니다.");
        }

        // 중복 전화번호 검사
        if (!Objects.isNull(command.getPhoneNumber()) && userPort.existsByPhoneNumber(command.getPhoneNumber())) {
            throw new IllegalArgumentException("이미 등록된 전화번호 입니다.");
        }

        // 등록할 사용자 객체 생성
        User user = User.builder()
                .email(command.getEmail())
                .password(passwordEncoder.encode(command.getPassword()))
                .name(command.getName())
                .phoneNumber(command.getPhoneNumber())
                .buildingNumber(command.getBuildingNumber())
                .profileImage(command.getProfileImage())
                .introduction(command.getIntroduction())
                .build();

        return userPort.save(user);
    }

    @Override
    public LoginResponse login(LoginCommand command) {
        // 이메일로 사용자 찾기
        User user = userPort.findByEmail(command.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 이메일입니다."));

        // 비밀번호 검증
        if (!passwordEncoder.matches(command.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 사용자가 활성화 상태인지 확인
        if (!user.isActive()) {
            throw new IllegalArgumentException("비활성화 된 계정입니다.");
        }

        // 로그인 시간 업데이트
        user.updateLoginTime();
        userPort.save(user);
        
        // JWT 토큰 생성
        GenerateTokenUseCase.GenerateTokenCommand tokenCommand = GenerateTokenUseCase.GenerateTokenCommand.builder()
                .email(user.getEmail())
                .userId(user.getId())
                .build();
        
        GenerateTokenUseCase.TokenResponse tokenResponse = generateTokenUseCase.generateToken(tokenCommand);
        
        // 로그인 응답 생성
        return LoginResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .accessToken(tokenResponse.getAccessToken())
                .refreshToken(tokenResponse.getRefreshToken())
                .expiresIn(tokenResponse.getExpiresIn())
                .build();
    }
}
