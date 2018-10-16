package com.platform.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-13 10:41:09
 */
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //订单序列号
    private String orderSn;
    //会员Id
    private Integer userId;
    //订单状态
    //订单相关状态字段设计，采用单个字段表示全部的订单状态
    //1xx 表示订单取消和删除等状态 0订单创建成功等待付款，　101订单已取消，　102订单已删除
    //2xx 表示订单支付状态　201订单已付款，等待发货
    //3xx 表示订单物流相关状态　300订单已发货， 301用户确认收货
    //4xx 表示订单退换货相关的状态　401 没有发货，退款　402 已收货，退款退货
    private Integer orderStatus;
    //发货状态 商品配送情况;0未发货,1已发货,2已收货,4退货
    private Integer shippingStatus;
    //付款状态 支付状态;0未付款;1付款中;2已付款
    private Integer payStatus;
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
    //补充说明
    private String postscript;
    //快递公司Id
    private Integer shippingId;
    //快递公司名称
    private String shippingName;
    //快递单号
    private String shippingNo;
    //付款
    private String payId;
    //
    private String payName;
    //快递费用
    private BigDecimal shippingFee;
    //实际需要支付的金额
    private BigDecimal actualPrice;
    //
    private Integer integral;
    //
    private BigDecimal integralMoney;
    //订单总价
    private BigDecimal orderPrice;
    //商品总价
    private BigDecimal goodsPrice;
    //新增时间
    private Date addTime;
    //确认时间
    private Date confirmTime;
    //付款时间
    private Date payTime;
    //配送费用
    private Integer freightPrice;
    //使用的优惠券id
    private Integer couponId;
    //
    private Integer parentId;
    //优惠价格
    private BigDecimal couponPrice;
    //
    private String callbackStatus;

    //订单类型 1：普通订单 2：团购订单 3：砍价订单 4: 直接购买
    private String orderType;

    //
    private String userName;

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

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
     * 设置：订单序列号
     */
    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    /**
     * 获取：订单序列号
     */
    public String getOrderSn() {
        return orderSn;
    }

    /**
     * 设置：会员Id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取：会员Id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置：订单状态
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 获取：订单状态
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置：发货状态
     */
    public void setShippingStatus(Integer shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    /**
     * 获取：发货状态
     */
    public Integer getShippingStatus() {
        return shippingStatus;
    }

    /**
     * 设置：付款状态
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * 获取：付款状态
     */
    public Integer getPayStatus() {
        return payStatus;
    }

    /**
     * 设置：收货人
     */
    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    /**
     * 获取：收货人
     */
    public String getConsignee() {
        return consignee;
    }

    /**
     * 设置：国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取：国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置：省
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取：省
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置：地市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取：地市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置：区县
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * 获取：区县
     */
    public String getDistrict() {
        return district;
    }

    public String getShippingNo() {
        return shippingNo;
    }

    public void setShippingNo(String shippingNo) {
        this.shippingNo = shippingNo;
    }

    /**
     * 设置：收货地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取：收货地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置：联系电话
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：联系电话
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置：补充说明
     */
    public void setPostscript(String postscript) {
        this.postscript = postscript;
    }

    /**
     * 获取：补充说明
     */
    public String getPostscript() {
        return postscript;
    }

    /**
     * 设置：快递公司Id
     */
    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
    }

    /**
     * 获取：快递公司Id
     */
    public Integer getShippingId() {
        return shippingId;
    }

    /**
     * 设置：快递公司名称
     */
    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    /**
     * 获取：快递公司名称
     */
    public String getShippingName() {
        return shippingName;
    }

    /**
     * 设置：付款
     */
    public void setPayId(String payId) {
        this.payId = payId;
    }

    /**
     * 获取：付款
     */
    public String getPayId() {
        return payId;
    }

    /**
     * 设置：
     */
    public void setPayName(String payName) {
        this.payName = payName;
    }

    /**
     * 获取：
     */
    public String getPayName() {
        return payName;
    }

    /**
     * 设置：快递费用
     */
    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }

    /**
     * 获取：快递费用
     */
    public BigDecimal getShippingFee() {
        return shippingFee;
    }

    /**
     * 设置：实际需要支付的金额
     */
    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    /**
     * 获取：实际需要支付的金额
     */
    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    /**
     * 设置：
     */
    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    /**
     * 获取：
     */
    public Integer getIntegral() {
        return integral;
    }

    /**
     * 设置：
     */
    public void setIntegralMoney(BigDecimal integralMoney) {
        this.integralMoney = integralMoney;
    }

    /**
     * 获取：
     */
    public BigDecimal getIntegralMoney() {
        return integralMoney;
    }

    /**
     * 设置：订单总价
     */
    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    /**
     * 获取：订单总价
     */
    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    /**
     * 设置：商品总价
     */
    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    /**
     * 获取：商品总价
     */
    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    /**
     * 设置：新增时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取：新增时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 设置：确认时间
     */
    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    /**
     * 获取：确认时间
     */
    public Date getConfirmTime() {
        return confirmTime;
    }

    /**
     * 设置：付款时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取：付款时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * 设置：配送费用
     */
    public void setFreightPrice(Integer freightPrice) {
        this.freightPrice = freightPrice;
    }

    /**
     * 获取：配送费用
     */
    public Integer getFreightPrice() {
        return freightPrice;
    }

    /**
     * 设置：使用的优惠券id
     */
    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    /**
     * 获取：使用的优惠券id
     */
    public Integer getCouponId() {
        return couponId;
    }

    /**
     * 设置：
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置：优惠价格
     */
    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    /**
     * 获取：优惠价格
     */
    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    /**
     * 设置：
     */
    public void setCallbackStatus(String callbackStatus) {
        this.callbackStatus = callbackStatus;
    }

    /**
     * 获取：
     */
    public String getCallbackStatus() {
        return callbackStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
