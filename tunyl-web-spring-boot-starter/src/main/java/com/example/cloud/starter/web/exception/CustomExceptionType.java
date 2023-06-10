package com.example.cloud.starter.web.exception;

/**
 * @author create by Tunyl on 2023/5/22
 * @version 1.0
 */
public enum CustomExceptionType {
    USER_INPUT_ERROR(400, "用户输入异常"),
    SYSTEM_ERROR(500, "系统服务异常"),
    OTHER_ERROR(999,"其他未知异常");

    CustomExceptionType(int code, String typeDesc) {
        this.typeDesc = typeDesc;
        this.code = code;
    }

    private String typeDesc;
    private int code;

    public String getTypeDesc() {
        return typeDesc;
    }

    public int getCode() {
        return code;
    }
}
