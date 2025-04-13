package com.juhyo.application.port.in.user;

import com.juhyo.domain.user.User;

import lombok.Builder;
import lombok.Getter;

public interface RegisterUserUseCase {
    User registerUser(RegisterUserCommand command);

    @Getter
    @Builder
    class RegisterUserCommand {
        private String email;
        private String password;
        private String name;
        private String phoneNumber;
        private String buildingNumber;
        private String profileImage;
        private String introduction;
    }
    
}
