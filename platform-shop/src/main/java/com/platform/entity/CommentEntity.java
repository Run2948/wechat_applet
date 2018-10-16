package com.platform.entity;

import java.io.Serializable;


/**
 * 实体
 * 表名 nideshop_comment
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-28 17:03:40
 */
public class CommentEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //类型
    private Integer typeId;
    //商品Id
    private Integer valueId;
    //储存为base64编码
    private String content;
    //记录时间
    private Long addTime;
    //状态
    private Integer status;
    //会员Id
    private Integer userId;

    /**
     * 翻译用字段
     */
    //会员
    private String userName;
    //商品
    private String valueName;

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
     * 设置：类型
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取：类型
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * 设置：
     */
    public void setValueId(Integer valueId) {
        this.valueId = valueId;
    }

    /**
     * 获取：
     */
    public Integer getValueId() {
        return valueId;
    }

    /**
     * 设置：储存为base64编码
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取：储存为base64编码
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置：记录时间
     */
    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取：记录时间
     */
    public Long getAddTime() {
        return addTime;
    }

    /**
     * 设置：状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置：会员Id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取：会员Id
     */
    public Integer getUserId() {
        return userId;
    }
}
