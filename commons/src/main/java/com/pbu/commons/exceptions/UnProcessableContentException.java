package com.pbu.commons.exceptions;

import lombok.extern.slf4j.Slf4j;

/**
 * @Project merchant-backend
 * @Date 11/12/26
 * @LocalTime 12:24AM
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
