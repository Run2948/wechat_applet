package com.platform.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 通用字典表实体
 * 表名 sys_macro
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-22 11:48:16
 */
public class SysMacroEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    //父级id
    private Long parentId;
    //名称
    private String name;
    //值
    private String value;
    //状态，0：隐藏   1：显示
    private Integer status;
    //类型,0:目录，1:参数配置
    private Integer type;
    //排序
    private Integer orderNum;
    //备注
    private String remark;
    //创建时间
    private Date gmtCreate;
    //修改时间
    private Date gmtModified;

    /**
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：父级id
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：父级id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置：名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：值
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取：值
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置：状态，0：隐藏   1：显示
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：状态，0：隐藏   1：显示
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置：类型,0:目录，1:参数配置
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取：类型,0:目录，1:参数配置
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置：排序
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取：排序
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * 设置：备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置：创建时间
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取：创建时间
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置：修改时间
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 获取：修改时间
     */
    public Date getGmtModified() {
        return gmtModified;
    }
}
