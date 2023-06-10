package com.example.cloud.aservice.rbac.model;

import com.example.cloud.starter.persistence.auto.model.SysApi;
import com.example.cloud.starter.web.tree.DataTree;


import java.util.List;

public class SysApiNode extends SysApi implements DataTree<SysApiNode> {

    private List<SysApiNode> children;

    @Override
    public Integer getParentId() {
        return super.getApiPid();
    }

    @Override
    public void setChildren(List<SysApiNode> children) {
        this.children = children;
    }

    @Override
    public List<SysApiNode> getChildren() {
        return this.children;
    }
}
