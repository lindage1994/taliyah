package org.iahsnil.gateway.admin.exception;

import lombok.extern.slf4j.Slf4j;
import org.iahsnil.common.web.exception.DefaultGlobalExceptionHandlerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandlerAdvice extends DefaultGlobalExceptionHandlerAdvice {
}