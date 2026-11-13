package com.pbu.commons.exceptions.model;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 * @Project merchant_app_backend
 * @Date 12/11/2026
 * @LocalTime 14:09
 **/
public record CustomExceptionPayLoad(String uri, int statusCode, String statusDescription, HttpStatus httpStatus, ZonedDateTime timeStamp) {
}
