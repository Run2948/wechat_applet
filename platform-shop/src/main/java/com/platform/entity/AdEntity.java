package com.platform.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 实体
 * 表名 nideshop_ad
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-19 09:37:35
 */
public class AdEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //广告位置Id
    private Integer adPositionId;
    //形式
    private Integer mediaType;
    //广告名称
    private String name;
    //链接
    private String link;
    //图片
    private String imageUrl;
    //内容
    private String content;
    //结束时间
    private Date endTime;
    //状态
    private Integer enabled;

    /**
     * 翻译字段用
     */
    //位置名称
    private String adPositionName;
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
     * 设置：广告位置Id
     */
    public void setAdPositionId(Integer adPositionId) {
        this.adPositionId = adPositionId;
    }

    /**
     * 获取：广告位置Id
     */
    public Integer getAdPositionId() {
        return adPositionId;
    }

    /**
     * 设置：形式
     */
    public void setMediaType(Integer mediaType) {
        this.mediaType = mediaType;
    }

    /**
     * 获取：形式
     */
    public Integer getMediaType() {
        return mediaType;
    }

    /**
     * 设置：广告名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：广告名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：链接
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * 获取：链接
     */
    public String getLink() {
        return link;
    }

    /**
     * 设置：图片
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * 获取：图片
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 设置：内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取：内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置：结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取：结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置：状态
     */
    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取：状态
     */
    public Integer getEnabled() {
        return enabled;
    }

    public String getAdPositionName() {
        return adPositionName;
    }

    public void setAdPositionName(String adPositionName) {
        this.adPositionName = adPositionName;
    }
}
