package com.example.cloud.aservice.rbac.controller;


import com.example.cloud.aservice.rbac.model.SysOrgNode;
import com.example.cloud.aservice.rbac.service.SysorgService;
import com.example.cloud.aservice.rbac.service.SysuserService;
import com.example.cloud.starter.persistence.auto.model.SysOrg;
import com.example.cloud.starter.persistence.auto.model.SysUser;
import com.example.cloud.starter.web.exception.AjaxResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/sysorg")
public class SysorgController {

  @Resource
  private SysorgService sysorgService;
  @Resource
  private SysuserService sysuserService;

  @PostMapping(value = "/tree")
  public List<SysOrgNode> tree(@RequestParam("username") String username,
                               @RequestParam("orgNameLike") String orgNameLike,
                               @RequestParam("orgStatus") Boolean orgStatus) {
    SysUser sysUser = sysuserService.getUserByUserName(username);
    return sysorgService.getOrgTreeById(sysUser.getOrgId(), orgNameLike, orgStatus);
  }

  @PostMapping(value = "/update")
  public AjaxResponse update(@RequestBody SysOrg sysOrg) {
    sysorgService.updateOrg(sysOrg);
    return AjaxResponse.success("更新组织机构成功！");
  }

  @PostMapping(value = "/add")
  public AjaxResponse add(@RequestBody SysOrg sysOrg) {
    sysorgService.addOrg(sysOrg);
    return AjaxResponse.success("新增组织机构成功！");
  }


  @PostMapping(value = "/delete")
  public AjaxResponse delete(@RequestBody SysOrg sysOrg) {
    sysorgService.deleteOrg(sysOrg);
    return AjaxResponse.success("删除组织机构成功!");
  }
}
