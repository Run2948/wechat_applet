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
	
	//新增

    //客户
    private String customers;
    //客户名称
    private String customers_name;
	//收货人
    private String consignee;
    //国家
    private String country;
    //省
    private String province;
    //地市
    private String city;
    //区县
    private String district;
    //收货地址
    private String address;
    //联系电话
    private String mobile;
    //快递公司Id
    private Integer shipping_id;
    //快递公司名称
    private String shipping_name;
    //快递号单号
    private String shipping_no;
    //发货状态 商品配送情况;0未发货,1已发货,2已收货,4退货
    private Integer shipping_status;

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
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getShipping_id() {
		return shipping_id;
	}
	public void setShipping_id(Integer shipping_id) {
		this.shipping_id = shipping_id;
	}
	public String getShipping_name() {
		return shipping_name;
	}
	public void setShipping_name(String shipping_name) {
		this.shipping_name = shipping_name;
	}
	public String getShipping_no() {
		return shipping_no;
	}
	public void setShipping_no(String shipping_no) {
		this.shipping_no = shipping_no;
	}
	public String getCustomers() {
		return customers;
	}
	public void setCustomers(String customers) {
		this.customers = customers;
	}
	public String getCustomers_name() {
		return customers_name;
	}
	public void setCustomers_name(String customers_name) {
		this.customers_name = customers_name;
	}
	public Integer getShipping_status() {
		return shipping_status;
	}
	public void setShipping_status(Integer shipping_status) {
		this.shipping_status = shipping_status;
	}
	
}
