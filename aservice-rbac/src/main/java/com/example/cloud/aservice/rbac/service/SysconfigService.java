package com.example.cloud.aservice.rbac.service;


import com.example.cloud.starter.persistence.auto.mapper.SysConfigMapper;
import com.example.cloud.starter.persistence.auto.model.SysConfig;
import com.example.cloud.starter.persistence.auto.model.SysConfigExample;
import com.example.cloud.starter.web.exception.CustomException;
import com.example.cloud.starter.web.exception.CustomExceptionType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SysconfigService {

  @Resource
  private SysConfigMapper sysConfigMapper;

  public List<SysConfig> queryConfigs(String configLik) {
      SysConfigExample sysConfigExample = new SysConfigExample();
      if(!StringUtils.isEmpty(configLik)){
        sysConfigExample.or().andParamNameLike("%"+ configLik+ "%");
        sysConfigExample.or().andParamKeyLike("%"+ configLik+ "%");
      }
      return sysConfigMapper.selectByExample(sysConfigExample);
  }

  public void updateConfig(SysConfig sysconfig){
    if(sysconfig.getId() == null){
      throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,
        "修改操作必须带主键");
    }else{
      sysConfigMapper.updateByPrimaryKeySelective(sysconfig);
    }
  }

  public void addConfig(SysConfig sysconfig){
    sysconfig.setCreateTime(new Date());
    sysConfigMapper.insert(sysconfig);
  }

  public void deleteConfig(Integer configId){
    sysConfigMapper.deleteByPrimaryKey(configId);
  }

}
