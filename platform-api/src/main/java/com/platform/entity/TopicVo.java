package com.platform.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-15 08:03:41
 */
public class TopicVo implements Serializable {
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
    private String item_pic_url;
    //子标题
    private String subtitle;
    //活动类别
    private Integer topic_category_id;
    //活动价格
    private BigDecimal price_info;
    //
    private String read_count;
    //场景图片链接
    private String scene_pic_url;
    //活动模板Id
    private Integer topic_template_id;
    //活动标签Id
    private Integer topic_tag_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getItem_pic_url() {
        return item_pic_url;
    }

    public void setItem_pic_url(String item_pic_url) {
        this.item_pic_url = item_pic_url;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Integer getTopic_category_id() {
        return topic_category_id;
    }

    public void setTopic_category_id(Integer topic_category_id) {
        this.topic_category_id = topic_category_id;
    }

    public BigDecimal getPrice_info() {
        return price_info;
    }

    public void setPrice_info(BigDecimal price_info) {
        this.price_info = price_info;
    }

    public String getRead_count() {
        return read_count;
    }

    public void setRead_count(String read_count) {
        this.read_count = read_count;
    }

    public String getScene_pic_url() {
        return scene_pic_url;
    }

    public void setScene_pic_url(String scene_pic_url) {
        this.scene_pic_url = scene_pic_url;
    }

    public Integer getTopic_template_id() {
        return topic_template_id;
    }

    public void setTopic_template_id(Integer topic_template_id) {
        this.topic_template_id = topic_template_id;
    }

    public Integer getTopic_tag_id() {
        return topic_tag_id;
    }

    public void setTopic_tag_id(Integer topic_tag_id) {
        this.topic_tag_id = topic_tag_id;
    }
}
