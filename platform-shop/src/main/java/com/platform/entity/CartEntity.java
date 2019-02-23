package com.platform.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-13 10:41:06
 */
public class CartEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //会员Id
    private Long userId;
    private String userName;
    //sessionId
    private String sessionId;
    //商品Id
    private Integer goodsId;
    //商品序列号
    private String goodsSn;
    //产品Id
    private Integer productId;
    //产品名称
    private String goodsName;
    //市场价
    private BigDecimal marketPrice;
    //零售价格
    private BigDecimal retailPrice;
    //数量
    private Integer number;
    //规格属性组成的字符串，用来显示用
    private String goodsSpecifitionNameValue;
    //product表对应的goods_specifition_ids
    private String goodsSpecifitionIds;
    //
    private Integer checked;
    //商品图片
    private String listPicUrl;

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
     * 设置：sessionId
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * 获取：sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * 设置：商品Id
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取：商品Id
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * 设置：商品序列号
     */
    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    /**
     * 获取：商品序列号
     */
    public String getGoodsSn() {
        return goodsSn;
    }

    /**
     * 设置：产品Id
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取：产品Id
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * 设置：产品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取：产品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置：市场价
     */
    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * 获取：市场价
     */
    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    /**
     * 设置：零售价格
     */
    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    /**
     * 获取：零售价格
     */
    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    /**
     * 设置：数量
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取：数量
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置：规格属性组成的字符串，用来显示用
     */
    public void setGoodsSpecifitionNameValue(String goodsSpecifitionNameValue) {
        this.goodsSpecifitionNameValue = goodsSpecifitionNameValue;
    }

    /**
     * 获取：规格属性组成的字符串，用来显示用
     */
    public String getGoodsSpecifitionNameValue() {
        return goodsSpecifitionNameValue;
    }

    /**
     * 设置：product表对应的goods_specifition_ids
     */
    public void setGoodsSpecifitionIds(String goodsSpecifitionIds) {
        this.goodsSpecifitionIds = goodsSpecifitionIds;
    }

    /**
     * 获取：product表对应的goods_specifition_ids
     */
    public String getGoodsSpecifitionIds() {
        return goodsSpecifitionIds;
    }

    /**
     * 设置：
     */
    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    /**
     * 获取：
     */
    public Integer getChecked() {
        return checked;
    }

    /**
     * 设置：商品图片
     */
    public void setListPicUrl(String listPicUrl) {
        this.listPicUrl = listPicUrl;
    }

    /**
     * 获取：商品图片
     */
    public String getListPicUrl() {
        return listPicUrl;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
