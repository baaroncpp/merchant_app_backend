package com.pbu.commons.exceptions;

import lombok.extern.slf4j.Slf4j;

/**
 * @Project merchant-backend
 * @Date 11/12/26
 * @LocalTime 12:41 PM
 **/
@Slf4j
public class DefaultException  extends RuntimeException{
    public DefaultException(String message, Object ... messageConstants){
        super(String.format(message, messageConstants));
        log.error(message);
    }

    public DefaultException(String message) {
        super(message);
        log.error(message);
    }
}

