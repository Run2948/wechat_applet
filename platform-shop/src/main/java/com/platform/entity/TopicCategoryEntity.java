package com.platform.entity;

import java.io.Serializable;


/**
 * 实体
 * 表名 nideshop_topic_category
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-20 15:41:56
 */
public class TopicCategoryEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //活动类别主题
    private String title;
    //活动类别图片链接
    private String picUrl;

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
     * 设置：活动类别主题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取：活动类别主题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置：活动类别图片链接
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     * 获取：活动类别图片链接
     */
    public String getPicUrl() {
        return picUrl;
    }
}
