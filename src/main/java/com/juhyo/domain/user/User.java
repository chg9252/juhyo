package com.juhyo.domain.user;

import java.time.LocalDateTime;

import lombok.Builder;


public class User {
    private Long id;
    private String email;
    private String password;
    private String name;        // 실명
    private String phoneNumber;

    // 주소 정보
    private String buildingNumber;  // 예: "101동 1001호"

    // 프로필 정보
    private String profileImage;
    private String introduction; // 자기소개
    private LocalDateTime joinDate;
    private LocalDateTime lastLoginDate;

    // 거래 관련 정보
    private Double reliability = 0.0; // 평점
    private Integer shareCount = 0; // 거래 횟수
    private Integer reportCount = 0; // 신고 횟수

    // 계정 상태
    private UserStatus status = UserStatus.ACTIVE;
    private UserRole role = UserRole.USER;

    protected User() {}

    @Builder
    public User(Long id, String email, String password, String name, String phoneNumber,
                String buildingNumber, String profileImage, String introduction, LocalDateTime joinDate, LocalDateTime lastLoginDate, 
                Double reliability, Integer shareCount, Integer reportCount, UserStatus status, UserRole role) {
            
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.buildingNumber = buildingNumber;
        this.profileImage = profileImage;
        this.introduction = introduction;
        this.joinDate = LocalDateTime.now();
    }

    // 개인정보 업데이트
    public void updatePersonalInfo(String name, String phoneNumber, String buildingNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.buildingNumber = buildingNumber;
    }

    // 비밀번호 변경
    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    // 계정 활성화
    public void activateAccount() {
        this.status = UserStatus.ACTIVE;
    }
    
    // 계정 비활성화
    public void disableAccount() {
        this.status = UserStatus.INACTIVE;
    }

    // 계정 정지
    public void banAccount() {
        this.status = UserStatus.BANNED;
    }

    // 계정 정지 해제
    public void unbanAccount() {
        this.status = UserStatus.ACTIVE;
    }

    // 로그인 시간 업데이트
    public void updateLoginTime() {
        this.lastLoginDate = LocalDateTime.now();
    }
}
