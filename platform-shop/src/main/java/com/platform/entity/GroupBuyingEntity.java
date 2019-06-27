package com.platform.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 实体
 * 表名 group_buying
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2019-06-13 22:00:12
 */
public class GroupBuyingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private String id;
    //商品ID
    private Integer goodsId;
    //商品名称
    private String goodsName;
    //已经团购人数
    private Integer groupNum;
    //成团需要人数
    private Integer successNum;
    //0未成功1已经拼团成功
    private Integer types;
    //供应商ID
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
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取：主键
     */
    public String getId() {
        return id;
    }
    /**
     * 设置：商品ID
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取：商品ID
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
     * 设置：已经团购人数
     */
    public void setGroupNum(Integer groupNum) {
        this.groupNum = groupNum;
    }

    /**
     * 获取：已经团购人数
     */
    public Integer getGroupNum() {
        return groupNum;
    }
    /**
     * 设置：成团需要人数
     */
    public void setSuccessNum(Integer successNum) {
        this.successNum = successNum;
    }

    /**
     * 获取：成团需要人数
     */
    public Integer getSuccessNum() {
        return successNum;
    }
    /**
     * 设置：0未成功1已经拼团成功
     */
    public void setTypes(Integer types) {
        this.types = types;
    }

    /**
     * 获取：0未成功1已经拼团成功
     */
    public Integer getTypes() {
        return types;
    }
}
