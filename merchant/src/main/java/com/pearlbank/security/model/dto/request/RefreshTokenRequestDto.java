package com.pearlbank.security.model.dto.request;

import com.pbu.commons.utils.Validate;

import static com.pearlbank.security.utils.SecurityMsgConstant.REFRESH_TOKEN_REQUIRED;

/**
 * @Project merchant_app_backend
 * @Date 13/11/2026
 * @LocalTime 10:21
 **/
public record RefreshTokenRequestDto(
        String refreshToken
) {
    public void validate() {
        Validate.notEmpty(refreshToken, REFRESH_TOKEN_REQUIRED);
    }
}
