package com.platform.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


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
    //优惠券金额
    private BigDecimal coupon_price;
    private Long merchantId;
    private Integer totalCount;
    //状态 1. 可用 2. 已用 3. 过期
    private Integer coupon_status;
    //使用开始时间
    private Date use_start_date;
    //使用结束时间
    private Date use_end_date;
    //最小使用金额
    private BigDecimal min_goods_amount;
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

	public BigDecimal getCoupon_price() {
		return coupon_price;
	}

	public void setCoupon_price(BigDecimal coupon_price) {
		this.coupon_price = coupon_price;
	}

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

	public Integer getCoupon_status() {
		return coupon_status;
	}

	public void setCoupon_status(Integer coupon_status) {
		this.coupon_status = coupon_status;
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
    
    
    
}
