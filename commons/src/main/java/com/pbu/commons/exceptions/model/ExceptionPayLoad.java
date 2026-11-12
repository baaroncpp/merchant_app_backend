package com.pbu.commons.exceptions.model;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 * @Project merchant-backend
 * @Date 11/12/26
 * @LocalTime 12:41 PM
 **/
public record ExceptionPayLoad(String uri, String message, HttpStatus httpStatus, ZonedDateTime timeStamp) {
}
