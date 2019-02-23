package com.platform.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 实体
 * 表名 nideshop_channel
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-22 19:19:56
 */
public class ChannelEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //id
    private Integer id;
    //名称
    private String name;
    //url
    private String url;
    //iconUrl
    private String iconUrl;
    //排序
    private Integer sortOrder;

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
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取：
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置：
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    /**
     * 获取：
     */
    public String getIconUrl() {
        return iconUrl;
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
}
