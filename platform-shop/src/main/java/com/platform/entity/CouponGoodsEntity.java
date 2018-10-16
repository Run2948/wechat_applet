package com.platform.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 优惠券关联商品实体
 * 表名 nideshop_coupon_goods
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-29 21:50:17
 */
public class CouponGoodsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //优惠券Id
    private Integer couponId;
    //商品id
    private Integer goodsId;

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
     * 设置：商品id
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取：商品id
     */
    public Integer getGoodsId() {
        return goodsId;
    }
}
