package com.pearlbank.security.model.dto.request;

import com.pbu.commons.utils.Validate;

import static com.pearlbank.security.utils.SecurityMsgConstant.*;

/**
 * @Project merchant_app_backend
 * @Date 13/11/2026
 * @LocalTime 10:06
 **/
public record AuthenticationRequestDto(
        String username,
        String password
) {
    public void validate(){
        Validate.notEmpty(username, USERNAME_REQUIRED);
        Validate.notEmpty(password, PASSWORD_REQUIRED);
    }
}
