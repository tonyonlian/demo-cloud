package com.example.cloud.aservice.rbac.model;


import com.example.cloud.starter.persistence.auto.model.SysOrg;
import com.example.cloud.starter.web.tree.DataTree;

import java.util.List;

public class SysOrgNode extends SysOrg implements DataTree<SysOrgNode> {

    private List<SysOrgNode> children;

    @Override
    public Integer getParentId() {
        return super.getOrgPid();
    }

    @Override
    public void setChildren(List<SysOrgNode> children) {
        this.children = children;
    }

    @Override
    public List<SysOrgNode> getChildren() {
        return this.children;
    }
}
