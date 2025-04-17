package com.juhyo.application.port.in.user;

import lombok.Builder;
import lombok.Getter;

public interface LoginUseCase {
    LoginResponse login(LoginCommand command);

    @Getter
    @Builder
    class LoginCommand {
        private String email;
        private String password;
    }
    
    @Getter
    @Builder
    class LoginResponse {
        private Long userId;
        private String email;
        private String name;
        private String accessToken;
        private String refreshToken;
        private long expiresIn;
    }
}
