package org.iahsnil.common.web.exception;


import lombok.extern.slf4j.Slf4j;
import org.iahsnil.common.exception.BaseException;
import org.iahsnil.common.exception.SystemErrorType;
import org.iahsnil.common.response.ResponseBean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

@Slf4j
public class DefaultGlobalExceptionHandlerAdvice {

    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public ResponseBean<?> missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.error("missing servlet request parameter exception:{}", ex.getMessage());
        return ResponseBean.fail(SystemErrorType.ARGUMENT_NOT_VALID);
    }

    @ExceptionHandler(value = {MultipartException.class})
    public ResponseBean<?> uploadFileLimitException(MultipartException ex) {
        log.error("upload file size limit:{}", ex.getMessage());
        return ResponseBean.fail(SystemErrorType.UPLOAD_FILE_SIZE_LIMIT);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseBean<?> serviceException(MethodArgumentNotValidException ex) {
        log.error("service exception:{}", ex.getMessage());
        return ResponseBean.fail(SystemErrorType.ARGUMENT_NOT_VALID, ex.getBindingResult().getFieldError());
    }

    @ExceptionHandler(value = {DuplicateKeyException.class})
    public ResponseBean<?> duplicateKeyException(DuplicateKeyException ex) {
        log.error("primary key duplication exception:{}", ex.getMessage());
        return ResponseBean.fail(SystemErrorType.DUPLICATE_PRIMARY_KEY);
    }

    @ExceptionHandler(value = {BaseException.class})
    public ResponseBean<?> baseException(BaseException ex) {
        log.error("base exception:{}", ex.getMessage());
        return ResponseBean.fail(ex.getErrorType());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseBean<?> exception() {
        return ResponseBean.fail();
    }

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseBean<?> throwable() {
        return ResponseBean.fail();
    }
}