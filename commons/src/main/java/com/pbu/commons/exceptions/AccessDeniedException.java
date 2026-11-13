package com.pbu.commons.exceptions;

import lombok.extern.slf4j.Slf4j;

/**
 * @Project merchant_app_backend
 * @Date 12/11/2026
 * @LocalTime 12:41
 **/
@Slf4j
public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException(String message, Object ... messageConstants){
        super(String.format(message, messageConstants));
        log.error(String.format(message, messageConstants));
    }

    public AccessDeniedException(String message) {
        super(message);
        log.error(message);
    }
}
