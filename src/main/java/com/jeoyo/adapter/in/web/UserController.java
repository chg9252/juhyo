package com.jeoyo.adapter.in.web;

import com.jeoyo.adapter.in.web.dto.request.LoginRequest;
import com.jeoyo.adapter.in.web.dto.request.RegisterUserRequest;
import com.jeoyo.adapter.in.web.dto.response.ApiResponse;
import com.jeoyo.adapter.in.web.dto.response.UserResponse;
import com.jeoyo.application.port.in.user.LoginUseCase;
import com.jeoyo.application.port.in.user.RegisterUserUseCase;
import com.jeoyo.domain.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUseCase loginUseCase;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(@Valid @RequestBody RegisterUserRequest request) {
        // useCase 명령객체 생성
        RegisterUserUseCase.RegisterUserCommand command = RegisterUserUseCase.RegisterUserCommand.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .buildingNumber(request.getBuildingNumber())
                .profileImage(request.getProfileImage())
                .introduction(request.getIntroduction())
                .build();

        // 회원가입 실행
        User saveUser = registerUserUseCase.registerUser(command);

        // User 도메인 객체를 응답 DTO로 변환
        UserResponse response = UserResponse.fromDomain(saveUser);

        return ResponseEntity.ok(ApiResponse.success("회원가입이 성공적으로 완료되었습니다.", response));
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginUseCase.LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
        // UserCae 명령 객체 생성
        LoginUseCase.LoginCommand command = LoginUseCase.LoginCommand.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        LoginUseCase.LoginResponse response  = loginUseCase.login(command);

        return ResponseEntity.ok(ApiResponse.success("로그인이 성공적으로 완료되었습니다.", response));
    }


    @GetMapping("/me")
    public ResponseEntity<ApiResponse<String>> me() {
        // 인증된 사용자 정보 확인 (JWT 인증 구현 후 작성)
        return ResponseEntity.ok(ApiResponse.success("인증된 사용자 정보입니다.", "JWT 인증 구현 필요"));
    }

    //... 추가 엔드포인트 (사용자 정보 수정, 비밀번호 변경 등)는 필요에 따라 구현


}
