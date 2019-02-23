package com.platform.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 实体
 * 表名 nideshop_coupon
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-19 12:53:26
 */
public class CouponEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //优惠券名称
    private String name;
    //金额
    private BigDecimal typeMoney;
    //发放方式
    private Integer sendType;
    //最小金额
    private BigDecimal minAmount;
    //最大金额
    private BigDecimal maxAmount;
    //发放时间
    private Date sendStartDate;
    //发放时间
    private Date sendEndDate;
    //使用开始时间
    private Date useStartDate;
    //使用结束时间
    private Date useEndDate;
    //最小商品金额
    private BigDecimal minGoodsAmount;

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
     * 设置：优惠券名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：优惠券名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：金额
     */
    public void setTypeMoney(BigDecimal typeMoney) {
        this.typeMoney = typeMoney;
    }

    /**
     * 获取：金额
     */
    public BigDecimal getTypeMoney() {
        return typeMoney;
    }

    /**
     * 设置：发放方式
     */
    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    /**
     * 获取：发放方式
     */
    public Integer getSendType() {
        return sendType;
    }

    /**
     * 设置：最小金额
     */
    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    /**
     * 获取：最小金额
     */
    public BigDecimal getMinAmount() {
        return minAmount;
    }

    /**
     * 设置：最大金额
     */
    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    /**
     * 获取：最大金额
     */
    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    /**
     * 设置：发放时间
     */
    public void setSendStartDate(Date sendStartDate) {
        this.sendStartDate = sendStartDate;
    }

    /**
     * 获取：发放时间
     */
    public Date getSendStartDate() {
        return sendStartDate;
    }

    /**
     * 设置：发放时间
     */
    public void setSendEndDate(Date sendEndDate) {
        this.sendEndDate = sendEndDate;
    }

    /**
     * 获取：发放时间
     */
    public Date getSendEndDate() {
        return sendEndDate;
    }

    /**
     * 设置：使用开始时间
     */
    public void setUseStartDate(Date useStartDate) {
        this.useStartDate = useStartDate;
    }

    /**
     * 获取：使用开始时间
     */
    public Date getUseStartDate() {
        return useStartDate;
    }

    /**
     * 设置：使用结束时间
     */
    public void setUseEndDate(Date useEndDate) {
        this.useEndDate = useEndDate;
    }

    /**
     * 获取：使用结束时间
     */
    public Date getUseEndDate() {
        return useEndDate;
    }

    /**
     * 设置：最小商品金额
     */
    public void setMinGoodsAmount(BigDecimal minGoodsAmount) {
        this.minGoodsAmount = minGoodsAmount;
    }

    /**
     * 获取：最小商品金额
     */
    public BigDecimal getMinGoodsAmount() {
        return minGoodsAmount;
    }
}
