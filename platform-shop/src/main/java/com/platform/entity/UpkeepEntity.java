package com.platform.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * 维护信息实体
 */
public class UpkeepEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //客户id
    private Integer customerId;
    //维护时间
    private Timestamp utime;
    //维护方式
    private String ctype;
    //地点
    private String place;
    //客户状态 1:有意向 2:无意向
    private String status;
    //礼品金额
    private BigDecimal giftPrice;
    //修改人id(维护人id)
    private String uid;
    //数据来源 1:用户购买商品,邮寄给客户 2:用户买了 服务性商品后，系统插入 3:用户自行维护
    private String dataFrom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Timestamp getUtime() {
        return utime;
    }

    public void setUtime(Timestamp utime) {
        this.utime = utime;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDataFrom() {
        return dataFrom;
    }

    public void setDataFrom(String dataFrom) {
        this.dataFrom = dataFrom;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getGiftPrice() {
        return giftPrice;
    }

    public void setGiftPrice(BigDecimal giftPrice) {
        this.giftPrice = giftPrice;
    }
}
