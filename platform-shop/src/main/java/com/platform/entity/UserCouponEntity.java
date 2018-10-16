package com.platform.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 实体
 * 表名 nideshop_user_coupon
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-19 15:40:33
 */
public class UserCouponEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //优惠券Id
    private Integer couponId;
    //优惠券数量
    private String couponNumber;
    //会员Id
    private Integer userId;
    //使用时间
    private Date usedTime;
    //领取时间
    private Date addTime;
    //订单Id
    private Integer orderId;

    /**
     * 翻译用字段
     */
    //会员名
    private String userName;

    //优惠劵名称
    private String couponName;


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
     * 设置：优惠券Id
     */
    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    /**
     * 获取：优惠券Id
     */
    public Integer getCouponId() {
        return couponId;
    }

    /**
     * 设置：优惠券数量
     */
    public void setCouponNumber(String couponNumber) {
        this.couponNumber = couponNumber;
    }

    /**
     * 获取：优惠券数量
     */
    public String getCouponNumber() {
        return couponNumber;
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

    /**
     * 设置：使用时间
     */
    public void setUsedTime(Date usedTime) {
        this.usedTime = usedTime;
    }

    /**
     * 获取：使用时间
     */
    public Date getUsedTime() {
        return usedTime;
    }

    /**
     * 设置：订单Id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取：订单Id
     */
    public Integer getOrderId() {
        return orderId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
