package com.pbu.commons.exceptions.model;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 * @Project merchant-backend
 * @Date 11/12/26
 * @LocalTime 2:09PM
 **/
public record CustomExceptionPayLoad(String uri, int statusCode, String statusDescription, HttpStatus httpStatus, ZonedDateTime timeStamp) {
}
