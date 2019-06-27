package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 group_buying_detailed
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2019-06-13 22:00:13
 */
public class GroupBuyingDetailedVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //团购主表ID
    private String groupBuyingId;
    //用户ID
    private Long userId;
    //用户名称
    private String userName;
    //用户图像
    private String userImg;
    //购买时间
    private Date payTime;

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
     * 设置：团购主表ID
     */
    public void setGroupBuyingId(String groupBuyingId) {
        this.groupBuyingId = groupBuyingId;
    }

    /**
     * 获取：团购主表ID
     */
    public String getGroupBuyingId() {
        return groupBuyingId;
    }
    /**
     * 设置：用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取：用户ID
     */
    public Long getUserId() {
        return userId;
    }
    /**
     * 设置：用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取：用户名称
     */
    public String getUserName() {
        return userName;
    }
    /**
     * 设置：用户图像
     */
    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    /**
     * 获取：用户图像
     */
    public String getUserImg() {
        return userImg;
    }
    /**
     * 设置：购买时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取：购买时间
     */
    public Date getPayTime() {
        return payTime;
    }
}
