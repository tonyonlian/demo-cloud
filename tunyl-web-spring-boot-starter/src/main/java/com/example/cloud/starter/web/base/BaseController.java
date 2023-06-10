package com.example.cloud.starter.web.base;

import com.example.cloud.starter.web.exception.AjaxResponse;
import com.example.cloud.starter.web.exception.CustomExceptionType;

/**
 * @author create by Tunyl on 2023/5/23
 * @version 1.0
 */

public class BaseController {

    //通用hystrix回退方法
    public AjaxResponse commonFallbackMethod() {
        return AjaxResponse.error(CustomExceptionType.SYSTEM_ERROR,
                "系统繁忙，请稍后再试!");
    } 
}
