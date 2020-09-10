package org.iahsnil.common.exception;

public interface ErrorType {
    /**
     * 返回code
     *
     * @return 错误码
     */
    String getCode();

    /**
     * 返回message
     *
     * @return 错误信息
     */
    String getMessage();
}
