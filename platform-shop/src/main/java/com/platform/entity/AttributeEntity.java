package com.platform.entity;

import java.io.Serializable;


/**
 * 实体
 * 表名 nideshop_attribute
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-17 16:48:17
 */
public class AttributeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //id
    private Integer id;
    //所属种类
    private Integer attributeCategoryId;
    //名称
    private String name;
    //类型
    private Integer inputType;
    //值
    private String value;
    //排序
    private Integer sortOrder;

    /**
     * 翻译用字段
     */
    //所属种类名称
    private String categoryName;

    /**
     * 设置：
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：
     */
    public void setAttributeCategoryId(Integer attributeCategoryId) {
        this.attributeCategoryId = attributeCategoryId;
    }

    /**
     * 获取：
     */
    public Integer getAttributeCategoryId() {
        return attributeCategoryId;
    }

    /**
     * 设置：
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：
     */
    public void setInputType(Integer inputType) {
        this.inputType = inputType;
    }

    /**
     * 获取：
     */
    public Integer getInputType() {
        return inputType;
    }

    /**
     * 设置：
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取：
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置：
     */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * 获取：
     */
    public Integer getSortOrder() {
        return sortOrder;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
