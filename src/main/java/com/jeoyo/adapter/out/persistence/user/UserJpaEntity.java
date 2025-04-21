package com.jeoyo.adapter.out.persistence.user;

import java.time.LocalDateTime;
import java.util.UUID;

import com.jeoyo.domain.user.UserRole;
import com.jeoyo.domain.user.UserStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** 사용자 정보를 저장하는 JPA 엔티티 */
@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name; // 실명        

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String buildingNumber;  // 상세주소 정보  예: "101동 1001호"

    private String profileImage; // 프로필 이미지
    
    @Column(length = 500)
    private String introduction; // 자기소개

    @Column(nullable = false)
    private LocalDateTime joinDate; // 가입일

    private LocalDateTime lastLoginDate; // 마지막 로그인 일시

    @Column(nullable = false)
    private Double reliability; // 평점

    @Column(nullable = false)
    private Integer shareCount;// 거래 횟수

    @Column(nullable = false)
    private Integer reportCount; // 신고 횟수

    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE; // 계정 상태

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER; // 계정 권한

}
