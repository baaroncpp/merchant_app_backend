package com.pearlbank.security.repository;

import com.pearlbank.security.model.jpa.TRefreshToken;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @Project merchant_app_backend
 * @Date 13/11/2026
 * @LocalTime 11:20
 **/
@Repository
public interface TRefreshTokenRepository extends JpaRepository<TRefreshToken, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<TRefreshToken> findByTokenHash(String tokenHash);
    List<TRefreshToken> findAllByFamilyIdAndRevokedOnIsNull(String familyId);
    List<TRefreshToken> findAllByUserIdAndRevokedOnIsNull(Long userId);
}
