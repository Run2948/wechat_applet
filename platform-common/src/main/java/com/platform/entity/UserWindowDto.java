package com.platform.entity;

/**
 * 审批范围dto
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017年11月16日 下午10:43:36
 */

public class UserWindowDto {
    /**
     * 主键id 可能是：用户id，角色id，组织id
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 机构
     */
    private String deptId;

    /**
     * 审批者类型
     */
    private String userType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
}
