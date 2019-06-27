package com.platform.entity;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 实体
 * 表名 nideshop_brand
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-19 17:59:15
 */
public class BrandEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //品牌名称
    private String name;
    //图片
    private String listPicUrl;
    //描述
    private String simpleDesc;
    //图片
    private String picUrl;
    //排序
    private Integer sortOrder;
    //显示
    private Integer isShow;
    //
    private Double floorPrice;
    //app显示图片
    private String appListPicUrl;
    //新品牌
    private Integer isNew;
    //图片
    private String newPicUrl;
    //排序
    private Integer newSortOrder;
    //商户id
    private Long merchantId;

    public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
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
     * 设置：品牌名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：品牌名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：图片
     */
    public void setListPicUrl(String listPicUrl) {
        this.listPicUrl = listPicUrl;
    }

    /**
     * 获取：图片
     */
    public String getListPicUrl() {
        return listPicUrl;
    }

    /**
     * 设置：描述
     */
    public void setSimpleDesc(String simpleDesc) {
        this.simpleDesc = simpleDesc;
    }

    /**
     * 获取：描述
     */
    public String getSimpleDesc() {
        return simpleDesc;
    }

    /**
     * 设置：图片
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     * 获取：图片
     */
    public String getPicUrl() {
        return picUrl;
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
     * 设置：显示
     */
    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    /**
     * 获取：显示
     */
    public Integer getIsShow() {
        return isShow;
    }

    /**
     * 设置：
     */
    public void setFloorPrice(Double floorPrice) {
        this.floorPrice = floorPrice;
    }

    /**
     * 获取：
     */
    public Double getFloorPrice() {
        return floorPrice;
    }

    /**
     * 设置：app显示图片
     */
    public void setAppListPicUrl(String appListPicUrl) {
        this.appListPicUrl = appListPicUrl;
    }

    /**
     * 获取：app显示图片
     */
    public String getAppListPicUrl() {
        return appListPicUrl;
    }

    /**
     * 设置：新品牌
     */
    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    /**
     * 获取：新品牌
     */
    public Integer getIsNew() {
        return isNew;
    }

    /**
     * 设置：图片
     */
    public void setNewPicUrl(String newPicUrl) {
        this.newPicUrl = newPicUrl;
    }

    /**
     * 获取：图片
     */
    public String getNewPicUrl() {
        return newPicUrl;
    }

    /**
     * 设置：排序
     */
    public void setNewSortOrder(Integer newSortOrder) {
        this.newSortOrder = newSortOrder;
    }

    /**
     * 获取：排序
     */
    public Integer getNewSortOrder() {
        return newSortOrder;
    }
}
