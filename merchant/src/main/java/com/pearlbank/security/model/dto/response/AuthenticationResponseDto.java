package com.pearlbank.security.model.dto.response;

import com.pearlbank.user_mgt.model.dto.response.GroupResponseDto;

import java.util.List;

/**
 * @Project merchant_app_backend
 * @Date 13/11/2026
 * @LocalTime 10:23
 **/
public record AuthenticationResponseDto(
        Long id,
        String accessToken,
        String refreshToken,
        Long tokenExpiry,
        int tokenDuration,
        List<GroupResponseDto> groups,
        List<String> authorities
) {
}
