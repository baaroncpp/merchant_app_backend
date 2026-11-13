package com.pearlbank.security.model.dto;

/**
 * @Project merchant_app_backend
 * @Date 13/11/2026
 * @LocalTime 11:20
 **/
public record AccessTokenDto(
        String token,
        Long tokenExpiry,
        int tokenDuration
) {
}
