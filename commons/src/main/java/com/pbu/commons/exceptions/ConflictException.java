package com.pbu.commons.exceptions;

import lombok.extern.slf4j.Slf4j;

/**
 * @Project merchant-backend
 * @Date 11/12/26
 * @LocalTime 7:55PM
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