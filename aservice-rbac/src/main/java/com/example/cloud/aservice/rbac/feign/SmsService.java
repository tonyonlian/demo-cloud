package com.example.cloud.aservice.rbac.feign;

import com.example.cloud.starter.web.exception.AjaxResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// @FeignClient(name = "ASERVICE-SMS",
//         //configuration = FeignClientErrorDecoder.class,
//         fallback = com.example.cloud.aservice.rbac.feign.SmsServiceFallback.class)
@FeignClient("ASERVICE-SMS")
public interface SmsService {

  @PostMapping(value = "/sms/send")
  AjaxResponse send(@RequestParam("phoneNo") String phoneNo, @RequestParam("content") String content);

}
