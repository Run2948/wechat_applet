package com.platform.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 热闹关键词表实体
 * 表名 nideshop_keywords
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-25 21:23:41
 */
public class KeywordsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //
    private String keyword;
    //
    private Integer isHot;
    //
    private Integer isDefault;
    //
    private Integer isShow;
    //
    private Integer sortOrder;
    //关键词的跳转链接
    private String schemeUrl;
    //
    private Integer type;

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
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * 获取：
     */
    public String getKeyword() {
        return keyword;
    }
    /**
     * 设置：
     */
    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    /**
     * 获取：
     */
    public Integer getIsHot() {
        return isHot;
    }
    /**
     * 设置：
     */
    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * 获取：
     */
    public Integer getIsDefault() {
        return isDefault;
    }
    /**
     * 设置：
     */
    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    /**
     * 获取：
     */
    public Integer getIsShow() {
        return isShow;
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
    /**
     * 设置：关键词的跳转链接
     */
    public void setSchemeUrl(String schemeUrl) {
        this.schemeUrl = schemeUrl;
    }

    /**
     * 获取：关键词的跳转链接
     */
    public String getSchemeUrl() {
        return schemeUrl;
    }
    /**
     * 设置：
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取：
     */
    public Integer getType() {
        return type;
    }
}
