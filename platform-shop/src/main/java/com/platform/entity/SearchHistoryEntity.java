package com.platform.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.platform.utils.JsonTimeSerializer;

import java.io.Serializable;


/**
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-13 10:41:10
 */
public class SearchHistoryEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //关键字
    private String keyword;
    //搜索来源，如PC、小程序、APP等
    private String from;
    //搜索时间
    private Long addTime;
    //会员Id
    private String userId;
    private String userName;

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
     * 设置：关键字
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * 获取：关键字
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * 设置：搜索来源，如PC、小程序、APP等
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * 获取：搜索来源，如PC、小程序、APP等
     */
    public String getFrom() {
        return from;
    }

    /**
     * 设置：搜索时间
     */
    @JsonSerialize(using = JsonTimeSerializer.class)
    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取：搜索时间
     */
    public Long getAddTime() {
        return addTime;
    }

    /**
     * 设置：会员Id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取：会员Id
     */
    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
