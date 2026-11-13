package com.pbu.commons.exceptions;

import lombok.extern.slf4j.Slf4j;

/**
 * @Project merchant_app_backend
 * @Date 12/11/2026
 * @LocalTime 12:41
 **/
@Slf4j
public class CustIllegalArgumentException extends RuntimeException {
    public CustIllegalArgumentException(String message, Object... messageConstants) {
        super(String.format(message, messageConstants));
    }

    public CustIllegalArgumentException(String message) {
        super(message);
    }
}
