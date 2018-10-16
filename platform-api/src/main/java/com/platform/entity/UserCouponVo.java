package com.platform.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-15 08:03:41
 */
public class UserCouponVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //优惠券Id
    private Integer coupon_id;
    //优惠券数量
    private String coupon_number;
    //会员Id
    private Long user_id;
    //使用时间
    private Date used_time;
    //领取时间
    private Date add_time;
    //订单Id
    private Integer order_id;
    //来源key
    private String source_key;
    //分享人
    private Long referrer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(Integer coupon_id) {
        this.coupon_id = coupon_id;
    }

    public String getCoupon_number() {
        return coupon_number;
    }

    public void setCoupon_number(String coupon_number) {
        this.coupon_number = coupon_number;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Date getUsed_time() {
        return used_time;
    }

    public void setUsed_time(Date used_time) {
        this.used_time = used_time;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }


    public Date getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }

    public String getSource_key() {
        return source_key;
    }

    public void setSource_key(String source_key) {
        this.source_key = source_key;
    }

    public Long getReferrer() {
        return referrer;
    }

    public void setReferrer(Long referrer) {
        this.referrer = referrer;
    }
}
