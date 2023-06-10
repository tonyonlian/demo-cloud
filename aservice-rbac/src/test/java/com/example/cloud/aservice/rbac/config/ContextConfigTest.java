package com.example.cloud.aservice.rbac.config;

import com.example.cloud.starter.web.exception.AjaxResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.web.ConditionalOnEnabledResourceChain;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author create by Tunyl on 2023/5/23
 * @version 1.0
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public  class ContextConfigTest {
    
    @Resource
    private RestTemplate restTemplate;
    
    
    @Test
    public void testSmsService(){
        String uri = "http://ASERVICE-SMS/sms/send";
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("phoneNo","13245678907");
        params.add("content","测试远程服务调用");

        AjaxResponse response = restTemplate.postForObject(uri, params, AjaxResponse.class);
        System.out.println(response);
    }

}