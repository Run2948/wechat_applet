package com.platform.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-13 10:41:09
 */
public class RelatedGoodsEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//商品Id
	private Integer goodsId;
	//关联商品id
	private Integer relatedGoodsId;

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
	 * 设置：关联商品id
	 */
	public void setRelatedGoodsId(Integer relatedGoodsId) {
		this.relatedGoodsId = relatedGoodsId;
	}
	/**
	 * 获取：关联商品id
	 */
	public Integer getRelatedGoodsId() {
		return relatedGoodsId;
	}
}
