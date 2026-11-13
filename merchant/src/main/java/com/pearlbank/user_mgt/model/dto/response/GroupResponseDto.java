package com.pearlbank.user_mgt.model.dto.response;

import java.time.LocalDateTime;

/**
 * @Project merchant_app_backend
 * @Date 13/11/2026
 * @LocalTime 10:25
 **/
public record GroupResponseDto(
        Long id,
        LocalDateTime createdOn,
        LocalDateTime modifiedOn,
        String name,
        String note,
        Boolean isActive
) {
}
