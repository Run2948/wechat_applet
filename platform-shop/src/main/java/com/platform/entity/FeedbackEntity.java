package com.platform.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 实体
 * 表名 nideshop_feedback
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-23 15:03:25
 */
public class FeedbackEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer msgId;
    //父节点
//    private Integer parentId;
    //会员Id
    private Integer userId;
    //会员名称
    private String userName;

    //移动电话
    private String mobile;
    //邮件
//    private String userEmail;
    //标题
//    private String msgTitle;
    //类型
    private Integer feedType;
    //状态
    private Integer status;
    //详细内容
    private String content;
    //反馈时间
    private Date addTime;
    //图片
//    private String messageImg;
    //订单Id
//    private Integer orderId;
    //
//    private Integer msgArea;

    /**
     * 设置：主键
     */
    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    /**
     * 获取：主键
     */
    public Integer getMsgId() {
        return msgId;
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
     * 设置：会员名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取：会员名称
     */
    public String getUserName() {
        return userName;
    }


    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getFeedType() {
        return feedType;
    }

    public void setFeedType(Integer feedType) {
        this.feedType = feedType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
