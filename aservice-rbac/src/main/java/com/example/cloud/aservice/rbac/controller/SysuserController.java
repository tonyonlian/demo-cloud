package com.example.cloud.aservice.rbac.controller;

import com.example.cloud.starter.web.exception.CustomExceptionType;
import com.github.pagehelper.PageInfo;
// import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
// import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.example.cloud.aservice.rbac.service.SysuserService;
import com.example.cloud.starter.persistence.auto.model.SysUser;
import com.example.cloud.starter.persistence.rbac.model.SysUserOrg;
import com.example.cloud.starter.web.base.BaseController;
import com.example.cloud.starter.web.exception.AjaxResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

// @DefaultProperties(defaultFallback = "commonFallbackMethod")
@RestController
@RequestMapping("/sysuser")
public class SysuserController extends BaseController {
    @Resource
    private SysuserService sysuserService;

    @GetMapping(value = "/info")
    public SysUser info(@RequestParam("username") String username) {

        return sysuserService.getUserByUserName(username);
    }

    @PostMapping("/query")
    public PageInfo<SysUserOrg> query(@RequestParam("orgId") Integer orgId,
                                      @RequestParam("username") String username,
                                      @RequestParam("phone") String phone,
                                      @RequestParam("email") String email,
                                      @RequestParam("enabled") Boolean enabled,
                                      @RequestParam("createStartTime") Date createStartTime,
                                      @RequestParam("createEndTime") Date createEndTime,
                                      @RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize) {

        return sysuserService.queryUser(orgId, username, phone, email, enabled,
                createStartTime, createEndTime,
                pageNum, pageSize);
    }


    @PostMapping(value = "/update")
    public AjaxResponse update(@RequestBody SysUser sysUser) {
        sysuserService.updateUser(sysUser);
        return AjaxResponse.success("更新用户成功！");
    }

    @PostMapping(value = "/add")
    public AjaxResponse add(@RequestBody SysUser sysUser) {
        sysuserService.addUser(sysUser);
        return AjaxResponse.success("新增用户成功！");
    }

    @PostMapping(value = "/delete")
    public AjaxResponse delete(@RequestParam Integer userId) {
        sysuserService.deleteUser(userId);
        return AjaxResponse.success("删除用户成功!");
    }

    // @HystrixCommand
    @PostMapping(value = "/pwd/reset")
    @HystrixCommand(
            fallbackMethod = "pwdresetFallback",
            commandProperties = {
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"), // 统计窗口时间
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //  启用熔断功能
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "20"), // 20个请求失败触发熔断
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), // 请求错误率超过60%出发熔断
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "300000") // 熔断后开始尝试恢复的时间

            }
    )
    public AjaxResponse pwdreset(@RequestParam Integer userId) {
        sysuserService.pwdreset(userId);
        return AjaxResponse.success("重置密码成功!");
    }

    public AjaxResponse pwdresetFallback(@RequestParam Integer userId) {
        return AjaxResponse.error(CustomExceptionType.SYSTEM_ERROR,"系统繁忙，稍后在试");
    }

    @PostMapping(value = "/pwd/isdefault")
    public Boolean isdefault(@RequestParam String username) {

        return sysuserService.isdefault(username);
    }

    @PostMapping(value = "/pwd/change")
    public AjaxResponse pwdchange(@RequestParam String username,
                                  @RequestParam String oldPass,
                                  @RequestParam String newPass) {
        sysuserService.changePwd(username, oldPass, newPass);
        return AjaxResponse.success("修改密码成功!");
    }
}