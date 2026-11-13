package com.pearlbank.security.model.dto;

import java.time.LocalDateTime;

/**
 * @Project merchant_app_backend
 * @Date 13/11/2026
 * @LocalTime 11:20
 **/
public record RefreshTokenDto(
        Long userId,
        String token,
        LocalDateTime expiryDate
) {
}
