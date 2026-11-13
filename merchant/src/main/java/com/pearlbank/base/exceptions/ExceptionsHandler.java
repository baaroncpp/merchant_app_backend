package com.pearlbank.base.exceptions;

import com.pbu.commons.exceptions.*;
import com.pbu.commons.exceptions.model.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.pearlbank.base.utils.BaseMsgConstants.INTERNAL_ERROR;
import static com.pearlbank.base.utils.BaseMsgConstants.MALFORMED_REQUEST;
import static com.pearlbank.security.utils.SecurityMsgConstant.*;

/**
 * @Project merchant_app_backend
 * @Date 13/11/2026
 * @LocalTime 10:08
 **/
@Slf4j
@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = {ConflictException.class})
    public ResponseEntity<Object> handleConflictException(ConflictException conflictException, HttpServletRequest request){

        var httpStatus = HttpStatus.CONFLICT;

        var exceptionPayLoad = new ExceptionPayLoad(
                request.getRequestURI(),
                conflictException.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(exceptionPayLoad, httpStatus);
    }

    @ExceptionHandler(value = {UnProcessableContentException.class})
    public ResponseEntity<Object> handleUnProcessableContentException(UnProcessableContentException unProcessableContentException, HttpServletRequest request){

        var httpStatus = HttpStatus.UNPROCESSABLE_CONTENT;

        var exceptionPayLoad = new ExceptionPayLoad(
                request.getRequestURI(),
                unProcessableContentException.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(exceptionPayLoad, httpStatus);
    }

    @ExceptionHandler(value = {AcknowledgementRequestException.class})
    public ResponseEntity<CustomExceptionPayLoad> handleAcknowledgementRequestException(AcknowledgementRequestException acknowledgementRequestException,
                                                                                        HttpServletRequest request) {
        var customExceptionPayLoad = new CustomExceptionPayLoad(
                request.getRequestURI(),
                acknowledgementRequestException.getCode(),
                acknowledgementRequestException.getStatusNote(),
                acknowledgementRequestException.getHttpStatus(),
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(customExceptionPayLoad, acknowledgementRequestException.getHttpStatus());
    }

    @ExceptionHandler(value = {CustIllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(CustIllegalArgumentException illegalArgumentException, HttpServletRequest request){

        var httpStatus = HttpStatus.BAD_REQUEST;

        var exceptionPayLoad = new ExceptionPayLoad(
                request.getRequestURI(),
                illegalArgumentException.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(exceptionPayLoad, httpStatus);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(BadRequestException badRequestException, HttpServletRequest request){

        var httpStatus = HttpStatus.BAD_REQUEST;

        var exceptionPayLoad = new ExceptionPayLoad(
                request.getRequestURI(),
                badRequestException.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(exceptionPayLoad, httpStatus);
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException, HttpServletRequest request){

        var httpStatus = HttpStatus.NOT_FOUND;

        var exceptionPayLoad = new ExceptionPayLoad(
                request.getRequestURI(),
                resourceNotFoundException.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(exceptionPayLoad, httpStatus);
    }

    @ExceptionHandler(value = {InsufficientAuthenticationException.class})
    public ResponseEntity<Object> handleInsufficientAuthenticationException(InsufficientAuthenticationException insufficientAuthenticationException, HttpServletRequest request){

        var httpStatus = HttpStatus.FORBIDDEN;

        var exceptionPayLoad = new ExceptionPayLoad(
                request.getRequestURI(),
                insufficientAuthenticationException.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(exceptionPayLoad, httpStatus);
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException badCredentialsException, HttpServletRequest request){

        var httpStatus = HttpStatus.UNAUTHORIZED;

        var exceptionPayLoad = new ExceptionPayLoad(
                request.getRequestURI(),
                badCredentialsException.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(exceptionPayLoad, httpStatus);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException accessDeniedException, HttpServletRequest request){

        var httpStatus = HttpStatus.FORBIDDEN;

        var exceptionPayLoad = new ExceptionPayLoad(
                request.getRequestURI(),
                accessDeniedException.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(exceptionPayLoad, httpStatus);
    }

    @ExceptionHandler(value = {org.springframework.security.authentication.BadCredentialsException.class, UsernameNotFoundException.class})
    public ResponseEntity<Object> handleLoginFailureException(AuthenticationException authenticationException, HttpServletRequest request){

        var httpStatus = HttpStatus.UNAUTHORIZED;

        var exceptionPayLoad = new ExceptionPayLoad(
                request.getRequestURI(),
                INVALID_CREDENTIALS,
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(exceptionPayLoad, httpStatus);
    }

    @ExceptionHandler(value = {AuthenticationException.class})
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException authenticationException, HttpServletRequest request){

        log.debug("Authentication failed on {}: {}", request.getRequestURI(), authenticationException.getMessage());

        var httpStatus = HttpStatus.UNAUTHORIZED;

        var exceptionPayLoad = new ExceptionPayLoad(
                request.getRequestURI(),
                INVALID_TOKEN,
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(exceptionPayLoad, httpStatus);
    }

    @ExceptionHandler(value = {org.springframework.security.access.AccessDeniedException.class})
    public ResponseEntity<Object> handleSpringAccessDeniedException(org.springframework.security.access.AccessDeniedException accessDeniedException, HttpServletRequest request){

        var httpStatus = HttpStatus.FORBIDDEN;

        var exceptionPayLoad = new ExceptionPayLoad(
                request.getRequestURI(),
                ACCESS_DENIED,
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(exceptionPayLoad, httpStatus);
    }

    @ExceptionHandler(value = {NoResourceFoundException.class, HttpRequestMethodNotSupportedException.class, HttpMediaTypeNotSupportedException.class, MissingServletRequestParameterException.class})
    public ResponseEntity<Object> handleSpringMvcException(Exception exception, HttpServletRequest request){

        var httpStatus = HttpStatus.valueOf(((ErrorResponse) exception).getStatusCode().value());

        var exceptionPayLoad = new ExceptionPayLoad(
                request.getRequestURI(),
                httpStatus.getReasonPhrase(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(exceptionPayLoad, httpStatus);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMalformedRequestException(Exception exception, HttpServletRequest request){

        var httpStatus = HttpStatus.BAD_REQUEST;

        var exceptionPayLoad = new ExceptionPayLoad(
                request.getRequestURI(),
                MALFORMED_REQUEST,
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(exceptionPayLoad, httpStatus);
    }

    @ExceptionHandler(value = {DefaultException.class})
    public ResponseEntity<Object> handleDefaultException(DefaultException defaultException, HttpServletRequest request){

        log.error(defaultException.getMessage(), defaultException);

        var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        var exceptionPayLoad = new ExceptionPayLoad(
                request.getRequestURI(),
                INTERNAL_ERROR,
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(exceptionPayLoad, httpStatus);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAllExceptions(Exception exception, HttpServletRequest request){

        log.error("Internal Server Error on {}", request.getRequestURI(), exception);

        var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        var exceptionPayLoad = new ExceptionPayLoad(
                request.getRequestURI(),
                INTERNAL_ERROR,
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(exceptionPayLoad, httpStatus);
    }
}
