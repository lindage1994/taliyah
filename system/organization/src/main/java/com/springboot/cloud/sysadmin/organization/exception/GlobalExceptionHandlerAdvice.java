package com.springboot.cloud.sysadmin.organization.exception;

import lombok.extern.slf4j.Slf4j;
import org.iahsnil.common.response.ResponseBean;
import org.iahsnil.common.web.exception.DefaultGlobalExceptionHandlerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandlerAdvice extends DefaultGlobalExceptionHandlerAdvice {

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseBean<?> userNotFound(UserNotFoundException ex) {
        log.error(ex.getMessage());
        return ResponseBean.fail(ex.getErrorType());
    }
}