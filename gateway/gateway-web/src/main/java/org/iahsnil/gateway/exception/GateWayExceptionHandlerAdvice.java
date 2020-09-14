package org.iahsnil.gateway.exception;

import com.springboot.cloud.common.core.exception.SystemErrorType;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.netty.channel.ConnectTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.iahsnil.common.response.ResponseBean;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Component
public class GateWayExceptionHandlerAdvice {

    @ExceptionHandler(value = {ResponseStatusException.class})
    public ResponseBean<SystemErrorType> handle(ResponseStatusException ex) {
        log.error("response status exception:{}", ex.getMessage());
        return ResponseBean.fail(SystemErrorType.GATEWAY_ERROR);
    }

    @ExceptionHandler(value = {ConnectTimeoutException.class})
    public ResponseBean<SystemErrorType> handle(ConnectTimeoutException ex) {
        log.error("connect timeout exception:{}", ex.getMessage());
        return ResponseBean.fail(SystemErrorType.GATEWAY_CONNECT_TIME_OUT);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseBean<SystemErrorType> handle(NotFoundException ex) {
        log.error("not found exception:{}", ex.getMessage());
        return ResponseBean.fail(SystemErrorType.GATEWAY_NOT_FOUND_SERVICE);
    }

    @ExceptionHandler(value = {ExpiredJwtException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseBean<SystemErrorType> handle(ExpiredJwtException ex) {
        log.error("ExpiredJwtException:{}", ex.getMessage());
        return ResponseBean.fail(SystemErrorType.INVALID_TOKEN);
    }

    @ExceptionHandler(value = {SignatureException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseBean<SystemErrorType> handle(SignatureException ex) {
        log.error("SignatureException:{}", ex.getMessage());
        return ResponseBean.fail(SystemErrorType.INVALID_TOKEN);
    }

    @ExceptionHandler(value = {MalformedJwtException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseBean<SystemErrorType> handle(MalformedJwtException ex) {
        log.error("MalformedJwtException:{}", ex.getMessage());
        return ResponseBean.fail(SystemErrorType.INVALID_TOKEN);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseBean<?> handle(RuntimeException ex) {
        log.error("runtime exception:{}", ex.getMessage());
        return ResponseBean.fail();
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseBean<?> handle(Exception ex) {
        log.error("exception:{}", ex.getMessage());
        return ResponseBean.fail();
    }

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseBean<?> handle(Throwable throwable) {
        ResponseBean<?> responseBean = ResponseBean.fail();
        if (throwable instanceof ResponseStatusException) {
            responseBean = handle((ResponseStatusException) throwable);
        } else if (throwable instanceof ConnectTimeoutException) {
            responseBean = handle((ConnectTimeoutException) throwable);
        } else if (throwable instanceof NotFoundException) {
            responseBean = handle((NotFoundException) throwable);
        } else if (throwable instanceof RuntimeException) {
            responseBean = handle((RuntimeException) throwable);
        } else if (throwable instanceof Exception) {
            responseBean = handle((Exception) throwable);
        }
        return responseBean;
    }
}
