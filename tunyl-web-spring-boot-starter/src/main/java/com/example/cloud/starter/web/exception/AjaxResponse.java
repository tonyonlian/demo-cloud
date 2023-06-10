package com.example.cloud.starter.web.exception;

import lombok.Data;

/**
 * @author create by Tunyl on 2023/5/22
 * @version 1.0
 */
@Data
public class AjaxResponse {
    private boolean isok;
    private int code;
    private String message;
    private Object data;
    
    private AjaxResponse(){
        
    }

    public static AjaxResponse error(CustomException ex) {
        AjaxResponse response = new AjaxResponse();
        response.setIsok(false);
        response.setCode(ex.getCode());
        if (ex.getCode() == CustomExceptionType.USER_INPUT_ERROR.getCode()) {
            response.setMessage(ex.getMessage());
        } else if (ex.getCode() == CustomExceptionType.SYSTEM_ERROR.getCode()) {
            response.setMessage(ex.getMessage()+", 请将异常消息发给管理员！");
        }else {
            response.setMessage("系统出现的未知异常，请联系管理员！");
            
        }
        return response;
    }
    
    public static AjaxResponse error(CustomExceptionType customExceptionType,String errorMessage){
        AjaxResponse response = new AjaxResponse();
        response.setIsok(false);
        response.setCode(customExceptionType.getCode());
        response.setMessage(errorMessage);
        return response;
    }

    //请求处理成功时的数据响应
    public static AjaxResponse success() {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(true);
        resultBean.setCode(200);
        resultBean.setMessage("success");
        return resultBean;
    }

    //请求处理成功，并响应结果数据
    public  static AjaxResponse success(Object data) {
        AjaxResponse resultBean = AjaxResponse.success();
        resultBean.setData(data);
        return resultBean;
    }
}
