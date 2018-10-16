package com.platform.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-15 08:03:40
 */
public class GoodsVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //商品类型Id
    private Integer category_id;
    //商品序列号
    private String goods_sn;
    //名称
    private String name;
    //品牌Id
    private Integer brand_id;
    //商品序列号
    private Integer goods_number;
    //关键字
    private String keywords;
    //简明介绍
    private String goods_brief;
    //商品描述
    private String goods_desc;
    //上架
    private Integer is_on_sale;
    //添加时间
    private Date add_time;
    //排序
    private Integer sort_order;
    //删除状态
    private Integer is_delete;
    //属性类别
    private Integer attribute_category;
    //专柜价格
    private BigDecimal counter_price;
    //附加价格
    private BigDecimal extra_price;
    //是否新商品
    private Integer is_new;
    //商品单位
    private String goods_unit;
    //商品主图
    private String primary_pic_url;
    //商品列表图
    private String list_pic_url;
    //市场价
    private BigDecimal market_price;
    //零售价格(现价)
    private BigDecimal retail_price;
    //销售量
    private Integer sell_volume;
    //主sku　product_id
    private Integer primary_product_id;
    //单位价格，单价
    private BigDecimal unit_price;
    //推广描述
    private String promotion_desc;
    //推广标签
    private String promotion_tag;
    //APP专享价
    private BigDecimal app_exclusive_price;
    //是否是APP专属
    private Integer is_app_exclusive;
    //限购
    private Integer is_limited;
    //热销
    private Integer is_hot;
    //购物车中商品数量
    private Integer cart_num = 0;
    // 冗余
    // 产品Id
    private Integer product_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getGoods_sn() {
        return goods_sn;
    }

    public void setGoods_sn(String goods_sn) {
        this.goods_sn = goods_sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Integer brand_id) {
        this.brand_id = brand_id;
    }

    public Integer getGoods_number() {
        return goods_number;
    }

    public void setGoods_number(Integer goods_number) {
        this.goods_number = goods_number;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getGoods_brief() {
        return goods_brief;
    }

    public void setGoods_brief(String goods_brief) {
        this.goods_brief = goods_brief;
    }

    public String getGoods_desc() {
        return goods_desc;
    }

    public void setGoods_desc(String goods_desc) {
        this.goods_desc = goods_desc;
    }

    public Integer getIs_on_sale() {
        return is_on_sale;
    }

    public void setIs_on_sale(Integer is_on_sale) {
        this.is_on_sale = is_on_sale;
    }

    public Date getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }

    public Integer getSort_order() {
        return sort_order;
    }

    public void setSort_order(Integer sort_order) {
        this.sort_order = sort_order;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    public Integer getAttribute_category() {
        return attribute_category;
    }

    public void setAttribute_category(Integer attribute_category) {
        this.attribute_category = attribute_category;
    }

    public BigDecimal getCounter_price() {
        return counter_price;
    }

    public void setCounter_price(BigDecimal counter_price) {
        this.counter_price = counter_price;
    }

    public BigDecimal getExtra_price() {
        return extra_price;
    }

    public void setExtra_price(BigDecimal extra_price) {
        this.extra_price = extra_price;
    }

    public Integer getIs_new() {
        return is_new;
    }

    public void setIs_new(Integer is_new) {
        this.is_new = is_new;
    }

    public String getGoods_unit() {
        return goods_unit;
    }

    public void setGoods_unit(String goods_unit) {
        this.goods_unit = goods_unit;
    }

    public String getPrimary_pic_url() {
        return primary_pic_url;
    }

    public void setPrimary_pic_url(String primary_pic_url) {
        this.primary_pic_url = primary_pic_url;
    }

    public String getList_pic_url() {
        return list_pic_url;
    }

    public void setList_pic_url(String list_pic_url) {
        this.list_pic_url = list_pic_url;
    }

    public BigDecimal getRetail_price() {
        return retail_price;
    }

    public void setRetail_price(BigDecimal retail_price) {
        this.retail_price = retail_price;
    }

    public Integer getSell_volume() {
        return sell_volume;
    }

    public void setSell_volume(Integer sell_volume) {
        this.sell_volume = sell_volume;
    }

    public Integer getPrimary_product_id() {
        return primary_product_id;
    }

    public void setPrimary_product_id(Integer primary_product_id) {
        this.primary_product_id = primary_product_id;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    public String getPromotion_desc() {
        return promotion_desc;
    }

    public void setPromotion_desc(String promotion_desc) {
        this.promotion_desc = promotion_desc;
    }

    public String getPromotion_tag() {
        return promotion_tag;
    }

    public void setPromotion_tag(String promotion_tag) {
        this.promotion_tag = promotion_tag;
    }

    public BigDecimal getApp_exclusive_price() {
        return app_exclusive_price;
    }

    public void setApp_exclusive_price(BigDecimal app_exclusive_price) {
        this.app_exclusive_price = app_exclusive_price;
    }

    public Integer getIs_app_exclusive() {
        return is_app_exclusive;
    }

    public void setIs_app_exclusive(Integer is_app_exclusive) {
        this.is_app_exclusive = is_app_exclusive;
    }

    public Integer getIs_limited() {
        return is_limited;
    }

    public void setIs_limited(Integer is_limited) {
        this.is_limited = is_limited;
    }

    public Integer getIs_hot() {
        return is_hot;
    }

    public void setIs_hot(Integer is_hot) {
        this.is_hot = is_hot;
    }

    public Integer getCart_num() {
        return cart_num;
    }

    public void setCart_num(Integer cart_num) {
        this.cart_num = cart_num;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public BigDecimal getMarket_price() {
        return market_price;
    }

    public void setMarket_price(BigDecimal market_price) {
        this.market_price = market_price;
    }
}
