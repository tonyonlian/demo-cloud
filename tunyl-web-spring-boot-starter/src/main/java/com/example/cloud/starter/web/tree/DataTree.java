package com.example.cloud.starter.web.tree;

import java.util.List;

/**
 * @author create by Tunyl on 2023/5/23
 * @version 1.0
 */
public interface DataTree<T> {
    //维护树形关系：元素一
    public Integer getId();
    //维护树形关系：元素二
    public Integer getParentId();
    //子节点数组
    public void setChildren(List<T> children);

    public List<T> getChildren();
    
}
