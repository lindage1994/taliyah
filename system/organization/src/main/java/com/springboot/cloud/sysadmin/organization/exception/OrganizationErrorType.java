package com.springboot.cloud.sysadmin.organization.exception;

import lombok.Getter;
import org.iahsnil.common.exception.ErrorType;

@Getter
public enum OrganizationErrorType implements ErrorType {

    USER_NOT_FOUND("030100", "用户未找到！"),
    ROLE_NOT_FOUND("030200", "角色未找到！");

    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String message;

    OrganizationErrorType(String code, String mesg) {
        this.code = code;
        this.message = message;
    }
}
