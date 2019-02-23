package com.platform.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.platform.utils.JsonTimeSerializer;

import java.io.Serializable;


/**
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-13 10:41:06
 */
public class CollectEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //用户Id
    private Long userId;
    private String userName;
    //产品Id
    private Integer valueId;
    private String valueName;
    //添加时间
    private Long addTime;
    //是否提醒
    private Integer isAttention;
    //
    private Integer typeId;

    /**
     * 设置：主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：用户Id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取：用户Id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置：产品Id
     */
    public void setValueId(Integer valueId) {
        this.valueId = valueId;
    }

    /**
     * 获取：产品Id
     */
    public Integer getValueId() {
        return valueId;
    }

    /**
     * 设置：添加时间
     */
    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取：添加时间
     */
    @JsonSerialize(using = JsonTimeSerializer.class)
    public Long getAddTime() {
        return addTime;
    }

    /**
     * 设置：是否提醒
     */
    public void setIsAttention(Integer isAttention) {
        this.isAttention = isAttention;
    }

    /**
     * 获取：是否提醒
     */
    public Integer getIsAttention() {
        return isAttention;
    }

    /**
     * 设置：
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取：
     */
    public Integer getTypeId() {
        return typeId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }
}
