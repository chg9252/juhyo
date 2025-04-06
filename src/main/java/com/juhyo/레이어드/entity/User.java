package com.juhyo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;        // 실명

    @Column(nullable = false)
    private String phoneNumber;

    // 주소 정보
    @Column(nullable = false)
    private String buildingNumber;  // 예: "101동 1001호"

    // 프로필 정보
    private String profileImage;
    
    @Column(length = 500)
    private String introduction; // 자기소개

    @Column(nullable = false)
    private LocalDateTime joinDate;

    private LocalDateTime lastLoginDate;

    // 거래 관련 정보
    private Double reliability = 0.0; // 평점
    private Integer shareCount = 0; // 거래 횟수
    private Integer reportCount = 0; // 신고 횟수

    // 계정 상태
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;
} 