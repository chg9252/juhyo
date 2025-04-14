package com.juhyo.adapter.out.persistence.user;

import org.springframework.stereotype.Component;

import com.juhyo.domain.user.User;


/** 도메인 객체와 JPA 엔티티 간의 매핑을 처리하는 매퍼 클래스 */
@Component
public class UserMapper {
    public User toDomain(UserJpaEntity entity) {
        if (entity == null) {
            return null;
        }

        return User.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .name(entity.getName())
                .phoneNumber(entity.getPhoneNumber())
                .buildingNumber(entity.getBuildingNumber())
                .profileImage(entity.getProfileImage())
                .introduction(entity.getIntroduction())
                .joinDate(entity.getJoinDate())
                .lastLoginDate(entity.getLastLoginDate())
                .reliability(entity.getReliability())
                .shareCount(entity.getShareCount())
                .reportCount(entity.getReportCount())
                .role(entity.getRole())
                .status(entity.getStatus())
                .build();

    }

    public UserJpaEntity toEntity(User domain) {
        if (domain == null) {
            return null;
        }
        
        return UserJpaEntity.builder()
                .id(domain.getId())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .name(domain.getName())
                .phoneNumber(domain.getPhoneNumber())
                .buildingNumber(domain.getBuildingNumber())
                .profileImage(domain.getProfileImage())
                .introduction(domain.getIntroduction())
                .joinDate(domain.getJoinDate())
                .lastLoginDate(domain.getLastLoginDate())
                .reliability(domain.getReliability())
                .shareCount(domain.getShareCount())
                .reportCount(domain.getReportCount())
                .role(domain.getRole())
                .status(domain.getStatus())
                .build();
    }
    
}
