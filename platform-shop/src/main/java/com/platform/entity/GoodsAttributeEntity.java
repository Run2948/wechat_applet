package com.platform.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-13 10:41:08
 */
public class GoodsAttributeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//商品Id
	private Integer goodsId;
	//属性Id
	private Integer attributeId;
	//属性值
	private String value;

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
	 * 设置：属性Id
	 */
	public void setAttributeId(Integer attributeId) {
		this.attributeId = attributeId;
	}
	/**
	 * 获取：属性Id
	 */
	public Integer getAttributeId() {
		return attributeId;
	}
	/**
	 * 设置：属性值
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * 获取：属性值
	 */
	public String getValue() {
		return value;
	}
}
