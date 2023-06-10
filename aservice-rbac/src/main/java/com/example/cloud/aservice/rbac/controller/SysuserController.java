package com.example.cloud.aservice.rbac.controller;

import com.github.pagehelper.PageInfo;
// import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
// import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.example.cloud.aservice.rbac.service.SysuserService;
import com.example.cloud.starter.persistence.auto.model.SysUser;
import com.example.cloud.starter.persistence.rbac.model.SysUserOrg;
import com.example.cloud.starter.web.base.BaseController;
import com.example.cloud.starter.web.exception.AjaxResponse;
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
  public PageInfo<SysUserOrg> query(@RequestParam("orgId") Integer orgId ,
                                    @RequestParam("username") String username ,
                                    @RequestParam("phone") String phone,
                                    @RequestParam("email") String email,
                                    @RequestParam("enabled") Boolean enabled,
                                    @RequestParam("createStartTime") Date createStartTime,
                                    @RequestParam("createEndTime") Date createEndTime,
                                    @RequestParam("pageNum") Integer pageNum,
                                    @RequestParam("pageSize") Integer pageSize) {

    return sysuserService.queryUser(orgId,username,phone,email,enabled,
                                    createStartTime, createEndTime,
                                    pageNum,pageSize);
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
  public AjaxResponse pwdreset(@RequestParam Integer userId) {
    sysuserService.pwdreset(userId);
    return AjaxResponse.success("重置密码成功!");
  }

  @PostMapping(value = "/pwd/isdefault")
  public Boolean isdefault(@RequestParam String username) {

    return sysuserService.isdefault(username);
  }

  @PostMapping(value = "/pwd/change")
  public AjaxResponse pwdchange(@RequestParam String username,
                                @RequestParam String oldPass,
                                @RequestParam String newPass) {
    sysuserService.changePwd(username,oldPass,newPass);
    return AjaxResponse.success("修改密码成功!");
  }
}