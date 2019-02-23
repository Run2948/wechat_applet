package com.platform.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 实体
 * 表名 nideshop_topic
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-20 14:10:08
 */
public class TopicEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //活动主题
    private String title;
    //活动内容
    private String content;
    //化名
    private String avatar;
    //活动条例图片
    private String itemPicUrl;
    //子标题
    private String subtitle;
    //活动类别
    private Integer topicCategoryId;
    //活动价格
    private BigDecimal priceInfo;
    //
    private String readCount;
    //场景图片链接
    private String scenePicUrl;
    //活动模板Id
    private Integer topicTemplateId;
    //活动标签Id
    private Integer topicTagId;

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
     * 设置：活动主题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取：活动主题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置：活动内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取：活动内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置：化名
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取：化名
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置：活动条例图片
     */
    public void setItemPicUrl(String itemPicUrl) {
        this.itemPicUrl = itemPicUrl;
    }

    /**
     * 获取：活动条例图片
     */
    public String getItemPicUrl() {
        return itemPicUrl;
    }

    /**
     * 设置：子标题
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    /**
     * 获取：子标题
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * 设置：活动类别
     */
    public void setTopicCategoryId(Integer topicCategoryId) {
        this.topicCategoryId = topicCategoryId;
    }

    /**
     * 获取：活动类别
     */
    public Integer getTopicCategoryId() {
        return topicCategoryId;
    }

    /**
     * 设置：活动价格
     */
    public void setPriceInfo(BigDecimal priceInfo) {
        this.priceInfo = priceInfo;
    }

    /**
     * 获取：活动价格
     */
    public BigDecimal getPriceInfo() {
        return priceInfo;
    }

    /**
     * 设置：
     */
    public void setReadCount(String readCount) {
        this.readCount = readCount;
    }

    /**
     * 获取：
     */
    public String getReadCount() {
        return readCount;
    }

    /**
     * 设置：场景图片链接
     */
    public void setScenePicUrl(String scenePicUrl) {
        this.scenePicUrl = scenePicUrl;
    }

    /**
     * 获取：场景图片链接
     */
    public String getScenePicUrl() {
        return scenePicUrl;
    }

    /**
     * 设置：活动模板Id
     */
    public void setTopicTemplateId(Integer topicTemplateId) {
        this.topicTemplateId = topicTemplateId;
    }

    /**
     * 获取：活动模板Id
     */
    public Integer getTopicTemplateId() {
        return topicTemplateId;
    }

    /**
     * 设置：活动标签Id
     */
    public void setTopicTagId(Integer topicTagId) {
        this.topicTagId = topicTagId;
    }

    /**
     * 获取：活动标签Id
     */
    public Integer getTopicTagId() {
        return topicTagId;
    }
}
