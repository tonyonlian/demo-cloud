package com.example.cloud.aservice.rbac.model;



import com.example.cloud.starter.persistence.auto.model.SysMenu;
import com.example.cloud.starter.web.tree.DataTree;

import java.util.List;

public class SysMenuNode extends SysMenu implements DataTree<SysMenuNode> {

    private List<SysMenuNode> children;

    private String path;
    private String name;

    public String getPath() {
      return this.getUrl();
    }

    public String getName() {
      return this.getMenuName();
    }

    @Override
    public Integer getParentId() {
        return super.getMenuPid();
    }

    @Override
    public void setChildren(List<SysMenuNode> children) {
        this.children = children;
    }

    @Override
    public List<SysMenuNode> getChildren() {
        return this.children;
    }
}
