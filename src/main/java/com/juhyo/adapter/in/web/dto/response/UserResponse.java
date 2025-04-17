package com.juhyo.adapter.in.web.dto.response;

import com.juhyo.domain.user.User;
import com.juhyo.domain.user.UserRole;
import com.juhyo.domain.user.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private UUID id;
    private String email;
    private String name;
    private String phoneNumber;
    private String buildingNumber;
    private String profileImage;
    private String introduction;
    private LocalDateTime joinDate;
    private Double reliability;
    private Integer shareCount;
    private UserRole role;
    private UserStatus status;

    public static UserResponse fromDomain(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .buildingNumber(user.getBuildingNumber())
                .profileImage(user.getProfileImage())
                .introduction(user.getIntroduction())
                .joinDate(user.getJoinDate())
                .reliability(user.getReliability())
                .shareCount(user.getShareCount())
                .role(user.getRole())
                .status(user.getStatus())
                .build();
    }

}
