package com.juhyo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juhyo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // 이메일로 사용자 찾기
    Optional<User> findByEmail(String email); 

    // 전화번호로 사용자 찾기
    Optional<User> findByPhoneNumber(String phoneNumber);

    // 이메일 존재 여부 확인
    boolean existsByEmail(String email);
}
