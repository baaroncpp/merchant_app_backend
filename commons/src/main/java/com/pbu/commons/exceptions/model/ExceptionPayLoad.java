package com.pbu.commons.exceptions.model;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 * @Project merchant_app_backend
 * @Date 12/11/2026
 * @LocalTime 12:41
 **/
public record ExceptionPayLoad(String uri, String message, HttpStatus httpStatus, ZonedDateTime timeStamp) {
}
