package org.iahsnil.common.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.iahsnil.common.exception.BaseException;
import org.iahsnil.common.exception.ErrorType;
import org.iahsnil.common.exception.SystemErrorType;

import java.time.Instant;
import java.time.ZonedDateTime;

@ApiModel(description = "rest请求的返回模型，所有rest正常都返回该类的对象")
@Getter
public class ResponseBean<T> {

    public static final String SUCCESSFUL_CODE = "000000";
    public static final String SUCCESSFUL_MESSAGE = "处理成功";

    @ApiModelProperty(value = "处理结果code", required = true)
    private String code;
    @ApiModelProperty(value = "处理结果描述信息")
    private String message;
    @ApiModelProperty(value = "请求结果生成时间戳")
    private Instant time;
    @ApiModelProperty(value = "处理结果数据信息")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public ResponseBean() {
        this.time = ZonedDateTime.now().toInstant();
    }

    /**
     * @param errorType
     */
    public ResponseBean(ErrorType errorType) {
        this.code = errorType.getCode();
        this.message = errorType.getMessage();
        this.time = ZonedDateTime.now().toInstant();
    }

    /**
     * @param errorType
     * @param data
     */
    public ResponseBean(ErrorType errorType, T data) {
        this(errorType);
        this.data = data;
    }

    /**
     * 内部使用，用于构造成功的结果
     *
     * @param code
     * @param message
     * @param data
     */
    private ResponseBean(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.time = ZonedDateTime.now().toInstant();
    }

    /**
     * 快速创建成功结果并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static <T> ResponseBean<T> success(T data) {
        return new ResponseBean<>(SUCCESSFUL_CODE, SUCCESSFUL_MESSAGE, data);
    }

    /**
     * 快速创建成功结果
     *
     * @return Result
     */
    public static ResponseBean<?> success() {
        return success(null);
    }

    /**
     * 系统异常类没有返回数据
     *
     * @return Result
     */
    public static ResponseBean<?> fail() {
        return new ResponseBean<>(SystemErrorType.SYSTEM_ERROR);
    }

    /**
     * 系统异常类没有返回数据
     *
     * @param baseException
     * @return Result
     */
    public static ResponseBean<?> fail(BaseException baseException) {
        return fail(baseException, null);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static <T> ResponseBean<T> fail(BaseException baseException, T data) {
        return new ResponseBean<>(baseException.getErrorType(), data);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param errorType
     * @param data
     * @return Result
     */
    public static <T> ResponseBean<T> fail(ErrorType errorType, T data) {
        return new ResponseBean<>(errorType, data);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param errorType
     * @return Result
     */
    public static ResponseBean<ErrorType> fail(ErrorType errorType) {
        return ResponseBean.fail(errorType, null);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static <T> ResponseBean<T> fail(T data) {
        return new ResponseBean<>(SystemErrorType.SYSTEM_ERROR, data);
    }


    /**
     * 成功code=000000
     *
     * @return true/false
     */
    @JsonIgnore
    public boolean isSuccess() {
        return SUCCESSFUL_CODE.equals(this.code);
    }

    /**
     * 失败
     *
     * @return true/false
     */
    @JsonIgnore
    public boolean isFail() {
        return !isSuccess();
    }

}
