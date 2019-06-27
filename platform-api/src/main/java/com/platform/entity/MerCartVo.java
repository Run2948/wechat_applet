package com.platform.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhouhaisheng on 2019/3/27.
 */
public class MerCartVo implements Serializable {
    private static final long serialVersionUID = 1L;
    public  Long merchantId;
    public String merchantName;
    public List<CartVo> cartVoList;
    public BigDecimal freightPrice;//运费
    public BigDecimal orderTotalPrice;//订单总金额
    public BigDecimal actualPrice;//实际支付金额
    public List<CouponVo> userCouponList;//用户可用优惠券列表
    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public List<CartVo> getCartVoList() {
        return cartVoList;
    }

    public void setCartVoList(List<CartVo> cartVoList) {
        this.cartVoList = cartVoList;
    }

    public BigDecimal getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(BigDecimal orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public BigDecimal getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(BigDecimal freightPrice) {
        this.freightPrice = freightPrice;
    }

    public List<CouponVo> getUserCouponList() {
        return userCouponList;
    }

    public void setUserCouponList(List<CouponVo> userCouponList) {
        this.userCouponList = userCouponList;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
}
