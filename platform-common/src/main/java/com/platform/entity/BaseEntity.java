package com.platform.entity;

import java.util.Date;
import java.util.List;

/**
 * 对业务实体做公共属性
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017年11月16日 下午10:43:36
 */
public class BaseEntity {
    /**
     * 新增人
     */
    private String createId;
    /**
     * 修改者
     */
    private String updateId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 新增时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 部门(组织)ID【FK】,直接归属的组织ID
     */
    private Long deptId;
    /**
     * 机构ID【FK】(上级)
     */
    private String deptParentId;

    /**
     * 部门ids 部门数据权限
     */
    private List<Long> deptIdList;
    /**
     * 机构ids 机构数据权限
     */
    private List<String> deptParentIdList;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 机构名称
     */
    private String deptParentName;

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptParentId() {
        return deptParentId;
    }

    public void setDeptParentId(String deptParentId) {
        this.deptParentId = deptParentId;
    }

    public List<Long> getDeptIdList() {
        return deptIdList;
    }

    public void setDeptIdList(List<Long> deptIdList) {
        this.deptIdList = deptIdList;
    }

    public List<String> getDeptParentIdList() {
        return deptParentIdList;
    }

    public void setDeptParentIdList(List<String> deptParentIdList) {
        this.deptParentIdList = deptParentIdList;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptParentName() {
        return deptParentName;
    }

    public void setDeptParentName(String deptParentName) {
        this.deptParentName = deptParentName;
    }
}
