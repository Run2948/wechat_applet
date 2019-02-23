package com.platform.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.platform.utils.JsonDateSerializer;

import java.io.Serializable;
import java.util.List;


/**
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-15 08:03:40
 */
public class CommentVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //用户评论的类型;0评论的是商品,1评论的是文章
    private Integer type_id;
    //产品Id
    private Integer value_id;
    //储存为base64编码
    private String content;
    //记录时间
    private Long add_time;
    //状态 是否被管理员批准显示;1是;0未批准显示
    private Integer status;
    //会员Id
    private Long user_id;

    //会员Id
    private UserVo user_info;
    private List<CommentPictureVo> pic_list;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public Integer getValue_id() {
        return value_id;
    }

    public void setValue_id(Integer value_id) {
        this.value_id = value_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @JsonSerialize(using = JsonDateSerializer.class)
    public Long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Long add_time) {
        this.add_time = add_time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public UserVo getUser_info() {
        return user_info;
    }

    public void setUser_info(UserVo user_info) {
        this.user_info = user_info;
    }

    public List<CommentPictureVo> getPic_list() {
        return pic_list;
    }

    public void setPic_list(List<CommentPictureVo> pic_list) {
        this.pic_list = pic_list;
    }
}
