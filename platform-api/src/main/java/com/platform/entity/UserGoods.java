package com.platform.entity;

/**
 * 类名称：NideshopUserGoods<br> 
 * 类描述：<br>
 */
public class UserGoods {
	private Integer userGoodsId;
	private Long userId;
	private Integer goodsId;
	private String name;
	private String goodsBrief;
	private double retailPrice;
	private double marketPrice;
	private String primaryPicUrl;

	/**
	 * 设置:userGoodsId
	 */
	public void setUserGoodsId(Integer value) {
		this.userGoodsId = value;
	}
	/**
	 * 获取:userGoodsId
	 */
	public Integer getUserGoodsId() {
		return userGoodsId;
	}

	/**
	 * 设置:用户ID
	 */
	public void setUserId(Long value) {
		this.userId = value;
	}
	/**
	 * 获取:用户ID
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置:商品ID
	 */
	public void setGoodsId(Integer value) {
		this.goodsId = value;
	}
	/**
	 * 获取:商品ID
	 */
	public Integer getGoodsId() {
		return goodsId;
	}

	/**
	 * 设置:商品名称
	 */
	public void setName(String value) {
		this.name = value;
	}
	/**
	 * 获取:商品名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置:商品附标题
	 */
	public void setGoodsBrief(String value) {
		this.goodsBrief = value;
	}
	/**
	 * 获取:商品附标题
	 */
	public String getGoodsBrief() {
		return goodsBrief;
	}

	/**
	 * 设置:零售价格
	 */
	public void setRetailPrice(Long value) {
		this.retailPrice = value;
	}
	/**
	 * 获取:零售价格
	 */
	public double getRetailPrice() {
		return retailPrice;
	}

	/**
	 * 设置:marketPrice
	 */
	public void setMarketPrice(double value) {
		this.marketPrice = value;
	}
	/**
	 * 获取:marketPrice
	 */
	public double getMarketPrice() {
		return marketPrice;
	}

	/**
	 * 设置:商品主图
	 */
	public void setPrimaryPicUrl(String value) {
		this.primaryPicUrl = value;
	}
	/**
	 * 获取:商品主图
	 */
	public String getPrimaryPicUrl() {
		return primaryPicUrl;
	}

	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}
}