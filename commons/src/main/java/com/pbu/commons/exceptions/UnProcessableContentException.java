package com.pbu.commons.exceptions;

import lombok.extern.slf4j.Slf4j;

/**
 * @Project merchant_app_backend
 * @Date 12/11/2026
 * @LocalTime 00:24
 **/
@Slf4j
public class UnProcessableContentException extends RuntimeException {
    public UnProcessableContentException(String message, Object ... messageConstants){
        super(String.format(message, messageConstants));
        log.error(String.format(message, messageConstants));
    }

    public UnProcessableContentException(String message) {
        super(message);
        log.error(message);
    }
}
