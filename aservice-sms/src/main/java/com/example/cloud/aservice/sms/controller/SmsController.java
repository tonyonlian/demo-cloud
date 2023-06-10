package com.example.cloud.aservice.sms.controller;

import com.example.cloud.starter.web.exception.AjaxResponse;
import com.example.cloud.starter.web.exception.CustomException;
import com.example.cloud.starter.web.exception.CustomExceptionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * @author create by Tunyl on 2023/5/22
 * @version 1.0
 */
@RestController
@RequestMapping("/sms")
public class SmsController {
    private static final Logger log = LoggerFactory.getLogger(SmsController.class);
    @Value("${server.port}")
    private int port;

    @PostMapping("/send")
    public AjaxResponse send(@RequestParam String phoneNo, @RequestParam String content) {
        log.info("方法的端口：{}", port);
        if (content.isEmpty() || phoneNo.isEmpty()) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,"消息内容或手机号不能为空");
        }
        log.info("发送短消息：{}" , content);
        
        return AjaxResponse.success("短消息发送成功");
    }
}
