package com.platform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-15 08:03:40
 */
public class OrderVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //订单序列号
    private String order_sn;
    //会员Id
    private Long user_id;
    /*
    订单状态
    1xx 表示订单取消和删除等状态 0订单创建成功等待付款，　101订单已取消，　102订单已删除 . 103 订单已失效不可再次付款
    2xx 表示订单支付状态　201订单已付款，等待发货
    3xx 表示订单物流相关状态　300订单已发货， 301用户确认收货
    4xx 表示订单退换货相关的状态　401 没有发货，退款　402 已收货，退款退货
    */
    private Integer order_status;
    //发货状态 商品配送情况;0未发货,1已发货,2已收货,4退货
    private Integer shipping_status;
    //付款状态 支付状态;0未付款;1付款中;2已付款;4退款
    private Integer pay_status;
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
    private Integer shipping_id;
    //快递公司code
    private String shipping_code;
    //快递公司名称
    private String shipping_name;
    //快递号
    private String shipping_no;
    //付款
    private String pay_id;
    //
    private String pay_name;
    //快递费用
    private BigDecimal shipping_fee;
    //实际需要支付的金额
    private BigDecimal actual_price;
    // 积分
    private Integer integral;
    // 积分抵扣金额
    private BigDecimal integral_money;
    //订单总价
    private BigDecimal order_price;
    //商品总价
    private BigDecimal goods_price;
    //新增时间
    private Date add_time;
    //确认时间
    private Date confirm_time;
    //付款时间
    private Date pay_time;
    //配送费用
    private Integer freight_price;
    //使用的优惠券id
    private Integer coupon_id;
    //
    private Integer parent_id;
    //优惠价格
    private BigDecimal coupon_price;
    //
    private Integer callback_status;
    //
    private Integer goodsCount; //订单的商品
    private String order_status_text;//订单状态的处理
    private Map handleOption; //可操作的选项
    private BigDecimal full_cut_price; //订单满减
    private String full_region;//区县
    //1购物车、2普通、3秒杀、4团购
    private String order_type; // 订单状态
    private String goods_name;//商品名称
    private String list_pic_url;//图片地址
    private String goods_id;//商品ID
    private BigDecimal all_price;//全部总价
    private String all_order_id;//总订单ID
    //新增
    //推广人id
    private int promoter_id;
    //本订单佣金
    private BigDecimal brokerage;
    //fx状态 默认为0是没有分润金额，已分润状态变成1
    private Integer fx_status;
    //商户id
    private Integer merchant_id;
    //团购ID
    private String group_buying_id;

    public String getFull_region() {
    //    return full_region;
        if (StringUtils.isNotEmpty(this.full_region)){
            return full_region;
        } else{
            StringBuffer strBuff = new StringBuffer();
            if (StringUtils.isNotEmpty(this.country)){
                strBuff.append(this.country).append(" ");
            }
            if(StringUtils.isNotEmpty(this.province)){
                strBuff.append(this.province).append(" ");
            }
            if (StringUtils.isNotEmpty(this.city)){
                strBuff.append(this.city).append(" ");
            }
            if (StringUtils.isNotEmpty(this.district)){
                strBuff.append(this.district).append(" ");
            }
            this.full_region = strBuff.toString();
            return this.full_region;
        }
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public void setFull_region(String full_region) {
        this.full_region = full_region;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Integer getOrder_status() {
        return order_status;
    }

    public void setOrder_status(Integer order_status) {
        this.order_status = order_status;
    }

    public Integer getShipping_status() {
        return shipping_status;
    }

    public void setShipping_status(Integer shipping_status) {
        this.shipping_status = shipping_status;
    }

    public Integer getPay_status() {
        return pay_status;
    }

    public void setPay_status(Integer pay_status) {
        this.pay_status = pay_status;
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

    public String getPostscript() {
        return postscript;
    }

    public void setPostscript(String postscript) {
        this.postscript = postscript;
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

    public String getPay_id() {
        return pay_id;
    }

    public void setPay_id(String pay_id) {
        this.pay_id = pay_id;
    }

    public String getPay_name() {
        return pay_name;
    }

    public void setPay_name(String pay_name) {
        this.pay_name = pay_name;
    }

    public BigDecimal getShipping_fee() {
        return shipping_fee;
    }

    public void setShipping_fee(BigDecimal shipping_fee) {
        this.shipping_fee = shipping_fee;
    }

    public BigDecimal getActual_price() {
        return actual_price;
    }

    public void setActual_price(BigDecimal actual_price) {
        this.actual_price = actual_price;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public BigDecimal getIntegral_money() {
        return integral_money;
    }

    public void setIntegral_money(BigDecimal integral_money) {
        this.integral_money = integral_money;
    }

    public BigDecimal getOrder_price() {
        return order_price;
    }

    public void setOrder_price(BigDecimal order_price) {
        this.order_price = order_price;
    }

    public BigDecimal getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(BigDecimal goods_price) {
        this.goods_price = goods_price;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getConfirm_time() {
        return confirm_time;
    }

    public void setConfirm_time(Date confirm_time) {
        this.confirm_time = confirm_time;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getPay_time() {
        return pay_time;
    }

    public void setPay_time(Date pay_time) {
        this.pay_time = pay_time;
    }

    public Integer getFreight_price() {
        return freight_price;
    }

    public void setFreight_price(Integer freight_price) {
        this.freight_price = freight_price;
    }

    public Integer getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(Integer coupon_id) {
        this.coupon_id = coupon_id;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public BigDecimal getCoupon_price() {
        return coupon_price;
    }

    public void setCoupon_price(BigDecimal coupon_price) {
        this.coupon_price = coupon_price;
    }

    public Integer getCallback_status() {
        return callback_status;
    }

    public void setCallback_status(Integer callback_status) {
        this.callback_status = callback_status;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getOrder_status_text() {
        if (null != order_status && StringUtils.isEmpty(order_status_text)) {
            order_status_text = "未付款";
            switch (order_status) {
                case 0:
                	order_status_text = "等待付款";//order_status_text = "未付款";
                    break;
                case 201:
                    order_status_text = "待发货";
                    break;
                case 300:
                    order_status_text = "待收货";
                    break;
                case 301:
                    order_status_text = "已完成";
                    break;
                case 200:
                    order_status_text = "已付款";
                    break;
                case 101:
                    order_status_text = "已取消";
                    break;
                case 401:
                    order_status_text = "已取消";
                    break;
                case 402:
                    order_status_text = "已退货";
                    break;
            }
        }
        return order_status_text;
    }

    public void setOrder_status_text(String order_status_text) {
        this.order_status_text = order_status_text;
    }

    //
    public Map getHandleOption() {
        handleOption = new HashMap();
        handleOption.put("cancel", false);//取消操作
        handleOption.put("delete", false);//删除操作
        handleOption.put("pay", false);//支付操作
        handleOption.put("comment", false);//评论操作
        handleOption.put("delivery", false);//确认收货操作
        handleOption.put("confirm", false);//完成订单操作
        handleOption.put("return", false); //退换货操作
        handleOption.put("buy", false); //再次购买

        //订单流程：　下单成功－》支付订单－》发货－》收货－》评论
        //订单相关状态字段设计，采用单个字段表示全部的订单状态
        //1xx 表示订单取消和删除等状态 0订单创建成功等待付款，　101订单已取消，　102订单已删除
        //2xx 表示订单支付状态　201订单已付款，等待发货
        //3xx 表示订单物流相关状态　300订单已发货， 301用户确认收货
        //4xx 表示订单退换货相关的状态　401 没有发货，退款　402 已收货，退款退货

        //如果订单已经取消或是已完成，则可删除和再次购买
        if (order_status == 101) {
//            handleOption.put("delete", true);
            handleOption.put("buy", true);
        }

        //如果订单没有被取消，且没有支付，则可支付，可取消
        if (order_status == 0) {
            handleOption.put("cancel", true);
            handleOption.put("pay", true);
        }

        //如果订单已付款，没有发货，则可退款操作
        if (order_status == 201) {
            handleOption.put("cancel", true);
        }

        //如果订单已经发货，没有收货，则可收货操作和退款、退货操作
        if (order_status == 300) {
//            handleOption.put("cancel", true);
            handleOption.put("confirm", true);
//            handleOption.put("return", true);
        }

        //如果订单已经支付，且已经收货，则可完成交易、评论和再次购买
        if (order_status == 301) {
            handleOption.put("comment", true);
            handleOption.put("buy", true);
        }
        return handleOption;
    }

    public void setHandleOption(Map handleOption) {
        this.handleOption = handleOption;
    }

    public BigDecimal getFull_cut_price() {
        return full_cut_price;
    }

    public void setFull_cut_price(BigDecimal full_cut_price) {
        this.full_cut_price = full_cut_price;
    }

    public String getShipping_code() {
        return shipping_code;
    }

    public void setShipping_code(String shipping_code) {
        this.shipping_code = shipping_code;
    }

    public String getShipping_no() {
        return shipping_no;
    }

    public void setShipping_no(String shipping_no) {
        this.shipping_no = shipping_no;
    }

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public String getList_pic_url() {
		return list_pic_url;
	}

	public void setList_pic_url(String list_pic_url) {
		this.list_pic_url = list_pic_url;
	}

	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}

	public BigDecimal getAll_price() {
		return all_price;
	}

	public void setAll_price(BigDecimal all_price) {
		this.all_price = all_price;
	}

	public String getAll_order_id() {
		return all_order_id;
	}

	public void setAll_order_id(String all_order_id) {
		this.all_order_id = all_order_id;
	}

    public int getPromoter_id() {
        return promoter_id;
    }

    public void setPromoter_id(int promoter_id) {
        this.promoter_id = promoter_id;
    }

    public BigDecimal getBrokerage() {
        return brokerage;
    }

    public void setBrokerage(BigDecimal brokerage) {
        this.brokerage = brokerage;
    }

	public Integer getFx_status() {
		return fx_status;
	}

	public void setFx_status(Integer fx_status) {
		this.fx_status = fx_status;
	}

	public Integer getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(Integer merchant_id) {
		this.merchant_id = merchant_id;
	}

	public String getGroup_buying_id() {
		return group_buying_id;
	}

	public void setGroup_buying_id(String group_buying_id) {
		this.group_buying_id = group_buying_id;
	}
	
}
