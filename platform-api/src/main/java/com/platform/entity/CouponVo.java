package com.platform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-15 08:03:41
 */
public class CouponVo implements Serializable {
    private static final long serialVersionUID = 1L;

    // 优惠券主键
    private Integer id;
    // 用户优惠券主键
    private Integer user_coupon_id;
    //优惠券名称
    private String name;
    //金额
    private BigDecimal type_money;
    //发放方式 0：按订单发放 1：按用户发放 2:商品转发送券 3：按商品发放 4:新用户注册 5：线下发放 6评价好评红包（固定或随机红包） 7包邮
    private Integer send_type;
    //最小金额
    private BigDecimal min_amount;
    //最大金额
    private BigDecimal max_amount;
    //发放时间
    @JsonFormat(pattern = "yyyy.MM.dd")
    private Date send_start_date;
    //发放时间
    @JsonFormat(pattern = "yyyy.MM.dd")
    private Date send_end_date;
    //使用开始时间
    @JsonFormat(pattern = "yyyy.MM.dd")
    private Date use_start_date;
    //使用结束时间
    @JsonFormat(pattern = "yyyy年MM月dd日")
    private Date use_end_date;
    //最小商品金额
    private BigDecimal min_goods_amount;
    //优惠券说明
    private String coupon_txt;
    //优惠券会员Id
    private String user_id;
    //优惠券编码
    private String coupon_number;
    //可用 1:可用 0：不可用
    private Integer enabled = 0;
    //转发次数
    private Integer min_transmit_num;
    //优惠券状态 1 可用 2 已用 3 过期
    private Integer coupon_status = 1;

    public Integer getUser_coupon_id() {
        return user_coupon_id;
    }

    public void setUser_coupon_id(Integer user_coupon_id) {
        this.user_coupon_id = user_coupon_id;
    }

    public Integer getCoupon_status() {
        return coupon_status;
    }

    public void setCoupon_status(Integer coupon_status) {
        this.coupon_status = coupon_status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getType_money() {
        return type_money;
    }

    public void setType_money(BigDecimal type_money) {
        this.type_money = type_money;
    }

    public Integer getSend_type() {
        return send_type;
    }

    public void setSend_type(Integer send_type) {
        this.send_type = send_type;
    }

    public BigDecimal getMin_amount() {
        return min_amount;
    }

    public void setMin_amount(BigDecimal min_amount) {
        this.min_amount = min_amount;
    }

    public BigDecimal getMax_amount() {
        return max_amount;
    }

    public void setMax_amount(BigDecimal max_amount) {
        this.max_amount = max_amount;
    }

    public Date getSend_start_date() {
        return send_start_date;
    }

    public void setSend_start_date(Date send_start_date) {
        this.send_start_date = send_start_date;
    }

    public Date getSend_end_date() {
        return send_end_date;
    }

    public void setSend_end_date(Date send_end_date) {
        this.send_end_date = send_end_date;
    }

    public Date getUse_start_date() {
        return use_start_date;
    }

    public void setUse_start_date(Date use_start_date) {
        this.use_start_date = use_start_date;
    }

    public Date getUse_end_date() {
        return use_end_date;
    }

    public void setUse_end_date(Date use_end_date) {
        this.use_end_date = use_end_date;
    }

    public BigDecimal getMin_goods_amount() {
        return min_goods_amount;
    }

    public void setMin_goods_amount(BigDecimal min_goods_amount) {
        this.min_goods_amount = min_goods_amount;
    }

    public String getCoupon_txt() {
        return coupon_txt;
    }

    public void setCoupon_txt(String coupon_txt) {
        this.coupon_txt = coupon_txt;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCoupon_number() {
        return coupon_number;
    }

    public void setCoupon_number(String coupon_number) {
        this.coupon_number = coupon_number;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getMin_transmit_num() {
        return min_transmit_num;
    }

    public void setMin_transmit_num(Integer min_transmit_num) {
        this.min_transmit_num = min_transmit_num;
    }
}
