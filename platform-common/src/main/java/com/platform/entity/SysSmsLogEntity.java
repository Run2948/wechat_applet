package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 发送短信日志
 * 表名 sys_sms_log
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-12-16 23:38:05
 */
public class SysSmsLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 操作人
     */
    private Long userId;
    /**
     * 必填参数。发送内容（1-500 个汉字）UTF-8编码
     */
    private String content;
    /**
     * 必填参数。手机号码。多个以英文逗号隔开
     */
    private String mobile;
    /**
     * 可选参数。发送时间，填写时已填写的时间发送，不填时为当前时间发送
     */
    private Date stime;
    /**
     * 必填参数。用户签名
     */
    private String sign;
    /**
     * 必填参数。固定值 pt
     */
    private String type;
    /**
     * 可选参数。扩展码，用户定义扩展码，只能为数字
     */
    private String extno;
    /**
     * 1成功 0失败
     */
    private Integer sendStatus;
    /**
     * 发送编号
     */
    private String sendId;
    /**
     * 无效号码数
     */
    private Integer invalidNum;
    /**
     * 成功提交数
     */
    private Integer successNum;
    /**
     * 黑名单数
     */
    private Integer blackNum;
    /**
     * 返回消息
     */
    private String returnMsg;

    //翻译
    /**
     * 操作人
     */
    private String userName;

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
     * 设置：操作人
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取：操作人
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置：必填参数。发送内容（1-500 个汉字）UTF-8编码
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取：必填参数。发送内容（1-500 个汉字）UTF-8编码
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置：必填参数。手机号码。多个以英文逗号隔开
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：必填参数。手机号码。多个以英文逗号隔开
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置：可选参数。发送时间，填写时已填写的时间发送，不填时为当前时间发送
     */
    public void setStime(Date stime) {
        this.stime = stime;
    }

    /**
     * 获取：可选参数。发送时间，填写时已填写的时间发送，不填时为当前时间发送
     */
    public Date getStime() {
        return stime;
    }

    /**
     * 设置：必填参数。用户签名
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * 获取：必填参数。用户签名
     */
    public String getSign() {
        return sign;
    }

    /**
     * 设置：必填参数。固定值 pt
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取：必填参数。固定值 pt
     */
    public String getType() {
        return type;
    }

    /**
     * 设置：可选参数。扩展码，用户定义扩展码，只能为数字
     */
    public void setExtno(String extno) {
        this.extno = extno;
    }

    /**
     * 获取：可选参数。扩展码，用户定义扩展码，只能为数字
     */
    public String getExtno() {
        return extno;
    }

    /**
     * 设置：1成功 0失败
     */
    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    /**
     * 获取：1成功 0失败
     */
    public Integer getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置：发送编号
     */
    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    /**
     * 获取：发送编号
     */
    public String getSendId() {
        return sendId;
    }

    /**
     * 设置：无效号码数
     */
    public void setInvalidNum(Integer invalidNum) {
        this.invalidNum = invalidNum;
    }

    /**
     * 获取：无效号码数
     */
    public Integer getInvalidNum() {
        return invalidNum;
    }

    /**
     * 设置：成功提交数
     */
    public void setSuccessNum(Integer successNum) {
        this.successNum = successNum;
    }

    /**
     * 获取：成功提交数
     */
    public Integer getSuccessNum() {
        return successNum;
    }

    /**
     * 设置：黑名单数
     */
    public void setBlackNum(Integer blackNum) {
        this.blackNum = blackNum;
    }

    /**
     * 获取：黑名单数
     */
    public Integer getBlackNum() {
        return blackNum;
    }

    /**
     * 设置：返回消息
     */
    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    /**
     * 获取：返回消息
     */
    public String getReturnMsg() {
        return returnMsg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
