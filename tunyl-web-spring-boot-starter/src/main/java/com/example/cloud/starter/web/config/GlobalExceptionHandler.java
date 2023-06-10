package com.example.cloud.starter.web.config;

import com.example.cloud.starter.web.exception.AjaxResponse;
import com.example.cloud.starter.web.exception.CustomException;
import com.example.cloud.starter.web.exception.CustomExceptionType;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author create by Tunyl on 2023/5/22
 * @version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    //处理程序员主动转换的自定义异常
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public AjaxResponse customerException(CustomException e) {
        if(e.getCode() == CustomExceptionType.SYSTEM_ERROR.getCode()){
            //400异常不需要持久化，将异常信息以友好的方式告知用户就可以
            //TODO 将500异常信息持久化处理，方便运维人员处理
        }
        return AjaxResponse.error(e);
    }
    //  方法参数无效异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public AjaxResponse handleBindException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR,
                fieldError.getDefaultMessage()));
    }

    // 绑定一场
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public AjaxResponse handleBindException(BindException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR,
                fieldError.getDefaultMessage()));
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public AjaxResponse handleIllegalArgumentException(IllegalArgumentException e) {
        return AjaxResponse.error(
                new CustomException(CustomExceptionType.USER_INPUT_ERROR,
                        e.getMessage())
        );
    }
    
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public AjaxResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException e){
        return AjaxResponse.error(
                new CustomException(CustomExceptionType.USER_INPUT_ERROR, e.getMessage())
        ); 
    }

    //处理程序员在程序中未能捕获（遗漏的）异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public AjaxResponse exception(Exception e) {
        System.out.println("==========异常");
        e.printStackTrace();
        return AjaxResponse.error(new CustomException(
                CustomExceptionType.OTHER_ERROR));
    }
    
}
