package com.example.cloud.starter.web.exception;

import javax.swing.plaf.metal.MetalMenuBarUI;

/**
 * @author create by Tunyl on 2023/5/22
 * @version 1.0
 */
public class CustomException extends RuntimeException {
    
    private int code;
    
    private String message;

    private CustomException() {
        
    }

    public CustomException(CustomExceptionType exceptionType) {
        this.code = exceptionType.getCode();
        this.message = exceptionType.getTypeDesc();
    }

    public CustomException(CustomExceptionType exceptionType,String message) {
        this.code = exceptionType.getCode();
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
