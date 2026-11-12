package com.pbu.commons.exceptions;

import lombok.extern.slf4j.Slf4j;

/**
 * @Project merchant-backend
 * @Date 11/12/26
 * @LocalTime 12:41 PM
 **/
@Slf4j
public class IllegalArgumentException extends RuntimeException {
    public IllegalArgumentException(String message, Object... messageConstants) {
        super(String.format(message, messageConstants));
    }

    public IllegalArgumentException(String message) {
        super(message);
    }
}
