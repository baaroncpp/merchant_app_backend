package com.pbu.commons.exceptions;

import lombok.extern.slf4j.Slf4j;

/**
 * @Project merchant_app_backend
 * @Date 12/11/2026
 * @LocalTime 19:55
 **/
@Slf4j
public class ConflictException  extends RuntimeException {
    public ConflictException(String message, Object... messageConstants) {
        super(String.format(message, messageConstants));
        log.error(String.format(message, messageConstants));
    }

    public ConflictException(String message) {
        super(message);
        log.error(message);
    }
}