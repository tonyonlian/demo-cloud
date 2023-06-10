package com.example.cloud.starter.persistence.auto.mapper;

import com.example.cloud.starter.persistence.auto.model.SysApi;
import com.example.cloud.starter.persistence.auto.model.SysApiExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysApiMapper {
    long countByExample(SysApiExample example);

    int deleteByExample(SysApiExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysApi record);

    int insertSelective(SysApi record);

    List<SysApi> selectByExample(SysApiExample example);

    SysApi selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysApi record, @Param("example") SysApiExample example);

    int updateByExample(@Param("record") SysApi record, @Param("example") SysApiExample example);

    int updateByPrimaryKeySelective(SysApi record);

    int updateByPrimaryKey(SysApi record);
}