package com.jeoyo.adapter.out.persistence.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** 사용자 정보를 저장하고 조회하는 리포지토리 */
@Repository
public interface UserRepository extends JpaRepository<UserJpaEntity, UUID>{

    Optional<UserJpaEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<UserJpaEntity> findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);

}
