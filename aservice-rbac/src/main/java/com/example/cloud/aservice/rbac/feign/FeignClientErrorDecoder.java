package com.example.cloud.aservice.rbac.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.example.cloud.starter.web.exception.AjaxResponse;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class FeignClientErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {

        try {
            if(response.body() != null){
                String jsonStr = Util.toString(response.body().asReader());

                //json字符串转对象
                ObjectMapper mapper = new ObjectMapper();
                AjaxResponse ajaxResponse = mapper.readValue(jsonStr, AjaxResponse.class);

                // 将AjaxResponse包装成 HystrixBadRequestException，FeignClient的Fallback策略
                if (!ajaxResponse.isIsok()) {
                    return new HystrixBadRequestException(ajaxResponse.getMessage());
                }
            }

        } catch (IOException ex) {
            return feign.FeignException.errorStatus(methodKey, response);
        }
        return feign.FeignException.errorStatus(methodKey, response);
    }


}