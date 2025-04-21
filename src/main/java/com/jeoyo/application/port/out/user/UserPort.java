package com.jeoyo.application.port.out.user;

import com.jeoyo.domain.user.User;
import java.util.Optional;
import java.util.UUID;

/* 이 인터페이스는 애플리케이션 코어가 데이터 저장소와 통신하는 방법을 정의하며, 실제 구현은 어댑터(Adapter)에서 이루어짐 */
public interface UserPort {

    // 사용자 저장(등록,수정)
    User save(User user);

    // ID로 사용자 조회
    Optional<User> findById(UUID id);

    // 이메일로 사용자 조회
    Optional<User> findByEmail(String email);

    // 이메일 존재 여부 확인
    boolean existsByEmail(String email);

    // 전화번호로 사용자 조회
    Optional<User> findByPhoneNumber(String phoneNumber);

    // 전화번호 존재 여부 확인
    boolean existsByPhoneNumber(String phoneNumber);

}
