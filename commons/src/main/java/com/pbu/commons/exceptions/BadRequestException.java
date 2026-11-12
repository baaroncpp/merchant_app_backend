package com.pbu.commons.exceptions;

import lombok.extern.slf4j.Slf4j;

/**
 * @Project merchant-backend
 * @Date 11/12/26
 * @LocalTime 12:41 PM
 **/
@Slf4j
public class BadRequestException extends RuntimeException{
    public BadRequestException(String message, Object ... messageConstants){
        super(String.format(message, messageConstants));
        log.error(String.format(message, messageConstants));
    }

    public BadRequestException(String message) {
        super(message);
        log.error(message);
    }
}
