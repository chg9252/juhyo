package com.juhyo.application.port.in.user;

import lombok.Builder;
import lombok.Getter;

public interface LoginUseCase {
    String login(LoginCommand command);

    @Getter
    @Builder
    class LoginCommand {
        private String email;
        private String password;
    }
}
