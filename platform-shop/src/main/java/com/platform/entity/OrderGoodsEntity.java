package com.platform.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 
 * 
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-13 10:41:09
 */
public class OrderGoodsEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//订单Id
	private Integer orderId;
	//商品id
	private Integer goodsId;
	//商品名称
	private String goodsName;
	//商品序列号
	private String goodsSn;
	//产品Id
	private Integer productId;
	//商品数量
	private Integer number;
	//市场价
	private BigDecimal marketPrice;
	//零售价格
	private BigDecimal retailPrice;
	//商品规格详情
	private String goodsSpecifitionNameValue;
	//虚拟商品
	private Integer isReal;
	//商品规格Ids
	private String goodsSpecifitionIds;
	//图片链接
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
	/**
	 * 设置：商品名称
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	/**
	 * 获取：商品名称
	 */
	public String getGoodsName() {
		return goodsName;
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
	 * 设置：商品数量
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	/**
	 * 获取：商品数量
	 */
	public Integer getNumber() {
		return number;
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
	 * 设置：商品规格详情
	 */
	public void setGoodsSpecifitionNameValue(String goodsSpecifitionNameValue) {
		this.goodsSpecifitionNameValue = goodsSpecifitionNameValue;
	}
	/**
	 * 获取：商品规格详情
	 */
	public String getGoodsSpecifitionNameValue() {
		return goodsSpecifitionNameValue;
	}
	/**
	 * 设置：虚拟商品
	 */
	public void setIsReal(Integer isReal) {
		this.isReal = isReal;
	}
	/**
	 * 获取：虚拟商品
	 */
	public Integer getIsReal() {
		return isReal;
	}
	/**
	 * 设置：商品规格Ids
	 */
	public void setGoodsSpecifitionIds(String goodsSpecifitionIds) {
		this.goodsSpecifitionIds = goodsSpecifitionIds;
	}
	/**
	 * 获取：商品规格Ids
	 */
	public String getGoodsSpecifitionIds() {
		return goodsSpecifitionIds;
	}
	/**
	 * 设置：图片链接
	 */
	public void setListPicUrl(String listPicUrl) {
		this.listPicUrl = listPicUrl;
	}
	/**
	 * 获取：图片链接
	 */
	public String getListPicUrl() {
		return listPicUrl;
	}
}
