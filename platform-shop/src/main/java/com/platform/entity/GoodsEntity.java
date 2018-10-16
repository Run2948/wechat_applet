package com.platform.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-13 10:41:08
 */
public class GoodsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //商品类型Id
    private Integer categoryId;
    //商品序列号
    private String goodsSn;
    //名称
    private String name;
    //品牌Id
    private Integer brandId;
    //商品序列号
    private Integer goodsNumber;
    //关键字
    private String keywords;
    //简明介绍
    private String goodsBrief;
    //商品描述
    private String goodsDesc;
    //上架
    private Integer isOnSale;
    //添加时间
    private Date addTime;
    //修改时间
    private Date updateTime;
    //排序
    private Integer sortOrder;
    //删除状态
    private Integer isDelete;
    //属性类别
    private Integer attributeCategory;
    //专柜价格
    private BigDecimal counterPrice;
    //附加价格
    private BigDecimal extraPrice;
    //是否新商品
    private Integer isNew;
    //商品单位
    private String goodsUnit;
    //商品主图
    private String primaryPicUrl;
    //商品列表图
    private String listPicUrl;
    //零售价格
    private BigDecimal retailPrice;
    //销售量
    private Integer sellVolume;
    //主sku　product_id
    private Integer primaryProductId;
    //单位价格，单价
    private BigDecimal unitPrice;
    //推广描述
    private String promotionDesc;
    //推广标签
    private String promotionTag;
    //APP专享价
    private BigDecimal appExclusivePrice;
    //是否是APP专属
    private Integer isAppExclusive;
    //限购
    private Integer isLimited;
    //热销
    private Integer isHot;
    //市场价
    private BigDecimal marketPrice;
    /**
     * 用户ID
     */
    private Long createUserId;
    /**
     * 用户ID
     */
    private Long createUserDeptId;
    /**
     * 用户ID
     */
    private Long updateUserId;

    List<GoodsAttributeEntity> attributeEntityList = new ArrayList<>();

    List<GoodsGalleryEntity> goodsImgList = new ArrayList<>();
    /**
     * 翻译用字段
     */
    //商品类型
    private String categoryName;
    //属性类别
    private String attributeCategoryName;
    //品牌
    private String brandName;

    public Long getCreateUserDeptId() {
        return createUserDeptId;
    }

    public void setCreateUserDeptId(Long createUserDeptId) {
        this.createUserDeptId = createUserDeptId;
    }

    public List<GoodsGalleryEntity> getGoodsImgList() {
        return goodsImgList;
    }

    public void setGoodsImgList(List<GoodsGalleryEntity> goodsImgList) {
        this.goodsImgList = goodsImgList;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getAttributeCategoryName() {
        return attributeCategoryName;
    }

    public void setAttributeCategoryName(String attributeCategoryName) {
        this.attributeCategoryName = attributeCategoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
     * 设置：商品类型Id
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取：商品类型Id
     */
    public Integer getCategoryId() {
        return categoryId;
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
     * 设置：名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：品牌Id
     */
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    /**
     * 获取：品牌Id
     */
    public Integer getBrandId() {
        return brandId;
    }

    /**
     * 设置：商品序列号
     */
    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    /**
     * 获取：商品序列号
     */
    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    /**
     * 设置：关键字
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * 获取：关键字
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * 设置：简明介绍
     */
    public void setGoodsBrief(String goodsBrief) {
        this.goodsBrief = goodsBrief;
    }

    /**
     * 获取：简明介绍
     */
    public String getGoodsBrief() {
        return goodsBrief;
    }

    /**
     * 设置：商品描述
     */
    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    /**
     * 获取：商品描述
     */
    public String getGoodsDesc() {
        return goodsDesc;
    }

    /**
     * 设置：上架
     */
    public void setIsOnSale(Integer isOnSale) {
        this.isOnSale = isOnSale;
    }

    /**
     * 获取：上架
     */
    public Integer getIsOnSale() {
        return isOnSale;
    }

    /**
     * 设置：添加时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取：添加时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 设置：排序
     */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * 获取：排序
     */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /**
     * 设置：删除状态
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取：删除状态
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 设置：属性类别
     */
    public void setAttributeCategory(Integer attributeCategory) {
        this.attributeCategory = attributeCategory;
    }

    /**
     * 获取：属性类别
     */
    public Integer getAttributeCategory() {
        return attributeCategory;
    }

    /**
     * 设置：专柜价格
     */
    public void setCounterPrice(BigDecimal counterPrice) {
        this.counterPrice = counterPrice;
    }

    /**
     * 获取：专柜价格
     */
    public BigDecimal getCounterPrice() {
        return counterPrice;
    }

    /**
     * 设置：附加价格
     */
    public void setExtraPrice(BigDecimal extraPrice) {
        this.extraPrice = extraPrice;
    }

    /**
     * 获取：附加价格
     */
    public BigDecimal getExtraPrice() {
        return extraPrice;
    }

    /**
     * 设置：是否新商品
     */
    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    /**
     * 获取：是否新商品
     */
    public Integer getIsNew() {
        return isNew;
    }

    /**
     * 设置：商品单位
     */
    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    /**
     * 获取：商品单位
     */
    public String getGoodsUnit() {
        return goodsUnit;
    }

    /**
     * 设置：商品主图
     */
    public void setPrimaryPicUrl(String primaryPicUrl) {
        this.primaryPicUrl = primaryPicUrl;
    }

    /**
     * 获取：商品主图
     */
    public String getPrimaryPicUrl() {
        return primaryPicUrl;
    }

    /**
     * 设置：商品列表图
     */
    public void setListPicUrl(String listPicUrl) {
        this.listPicUrl = listPicUrl;
    }

    /**
     * 获取：商品列表图
     */
    public String getListPicUrl() {
        return listPicUrl;
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
     * 设置：销售量
     */
    public void setSellVolume(Integer sellVolume) {
        this.sellVolume = sellVolume;
    }

    /**
     * 获取：销售量
     */
    public Integer getSellVolume() {
        return sellVolume;
    }

    /**
     * 设置：主sku　product_id
     */
    public void setPrimaryProductId(Integer primaryProductId) {
        this.primaryProductId = primaryProductId;
    }

    /**
     * 获取：主sku　product_id
     */
    public Integer getPrimaryProductId() {
        return primaryProductId;
    }

    /**
     * 设置：单位价格，单价
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * 获取：单位价格，单价
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * 设置：推广描述
     */
    public void setPromotionDesc(String promotionDesc) {
        this.promotionDesc = promotionDesc;
    }

    /**
     * 获取：推广描述
     */
    public String getPromotionDesc() {
        return promotionDesc;
    }

    /**
     * 设置：推广标签
     */
    public void setPromotionTag(String promotionTag) {
        this.promotionTag = promotionTag;
    }

    /**
     * 获取：推广标签
     */
    public String getPromotionTag() {
        return promotionTag;
    }

    /**
     * 设置：APP专享价
     */
    public void setAppExclusivePrice(BigDecimal appExclusivePrice) {
        this.appExclusivePrice = appExclusivePrice;
    }

    /**
     * 获取：APP专享价
     */
    public BigDecimal getAppExclusivePrice() {
        return appExclusivePrice;
    }

    /**
     * 设置：是否是APP专属
     */
    public void setIsAppExclusive(Integer isAppExclusive) {
        this.isAppExclusive = isAppExclusive;
    }

    /**
     * 获取：是否是APP专属
     */
    public Integer getIsAppExclusive() {
        return isAppExclusive;
    }

    /**
     * 设置：限购
     */
    public void setIsLimited(Integer isLimited) {
        this.isLimited = isLimited;
    }

    /**
     * 获取：限购
     */
    public Integer getIsLimited() {
        return isLimited;
    }

    /**
     * 设置：热销
     */
    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    /**
     * 获取：热销
     */
    public Integer getIsHot() {
        return isHot;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public List<GoodsAttributeEntity> getAttributeEntityList() {
        return attributeEntityList;
    }

    public void setAttributeEntityList(List<GoodsAttributeEntity> attributeEntityList) {
        this.attributeEntityList = attributeEntityList;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
