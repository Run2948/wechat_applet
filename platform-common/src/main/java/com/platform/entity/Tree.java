package com.platform.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 名称：Tree <br>
 * 描述：<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
public class Tree<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**********iview tree属性**************/
    /**
     * 标题
     */
    private String title;

    /**
     * 是否展开直子节点
     */
    private boolean expand = false;

    /**
     * 禁掉响应
     */
    private boolean disabled = false;
    /**
     * 禁掉 checkbox
     */
    private boolean disableCheckbox = false;
    /**
     * 是否选中子节点
     */
    private boolean selected = false;
    /**
     * 是否勾选(如果勾选，子节点也会全部勾选)
     */
    private boolean checked = false;

    private boolean leaf = false;
    /**
     * ztree属性
     */
    private Boolean open;

    private List<?> list;

    /**
     * 子节点属性数组
     */
    private List<?> children;
    private String value;
    private String label;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isDisableCheckbox() {
        return disableCheckbox;
    }

    public void setDisableCheckbox(boolean disableCheckbox) {
        this.disableCheckbox = disableCheckbox;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public List<?> getChildren() {
        return children;
    }

    public void setChildren(List<?> children) {
        this.children = children;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
