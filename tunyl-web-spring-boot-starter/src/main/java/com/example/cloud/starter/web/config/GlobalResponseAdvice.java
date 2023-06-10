package com.example.cloud.starter.web.config;

import com.example.cloud.starter.web.exception.AjaxResponse;
import com.example.cloud.starter.web.exception.CustomExceptionType;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author create by Tunyl on 2023/5/22
 * @version 1.0
 */
@Component
@ControllerAdvice
public class GlobalResponseAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(mediaType.equalsTypeAndSubtype(MediaType.APPLICATION_JSON)){
            if (body instanceof AjaxResponse) {
                if(CustomExceptionType.OTHER_ERROR.getCode() != ((AjaxResponse)body).getCode()) {
                    serverHttpResponse.setStatusCode(HttpStatus.valueOf(((AjaxResponse) body).getCode()));
                }
                return body;
            }else{
                AjaxResponse ajaxResponse = AjaxResponse.success(body);
                serverHttpResponse.setStatusCode(HttpStatus.valueOf(
                        ajaxResponse.getCode())
                );
                return AjaxResponse.success(body);
            }
        }
        return body;
    }
}
