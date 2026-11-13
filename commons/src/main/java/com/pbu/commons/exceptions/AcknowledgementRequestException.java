package com.pbu.commons.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

/**
 * @Project merchant_app_backend
 * @Date 12/11/2026
 * @LocalTime 16:10
 **/
@Slf4j
public class AcknowledgementRequestException  extends RuntimeException{

    private final HttpStatus httpStatus;
    private final int code;
    private final String statusNote;

    public AcknowledgementRequestException(HttpStatus httpStatus,
                                           int code,
                                           String statusNote,
                                           Object ... messageConstants){
        super(String.format(statusNote, messageConstants));
        this.httpStatus = httpStatus;
        this.code = code;
        this.statusNote = statusNote;
    }

    public AcknowledgementRequestException(HttpStatus httpStatus,
                                           int code,
                                           String statusNote) {
        super(statusNote);
        this.httpStatus = httpStatus;
        this.code = code;
        this.statusNote = statusNote;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public int getCode() {
        return code;
    }

    public String getStatusNote() {
        return statusNote;
    }
}

