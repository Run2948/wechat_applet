package com.platform.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-15 08:03:41
 */
public class UserVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Long userId;
    //会员名称
    private String username;
    //会员密码
    private String password;
    //性别
    private Integer gender;
    //出生日期
    private Date birthday;
    //注册时间
    private Date register_time;
    //最后登录时间
    private Date last_login_time;
    //最后登录Ip
    private String last_login_ip;
    //会员等级
    private Integer user_level_id;
    //别名
    private String nickname;
    //手机号码
    private String mobile;
    //注册Ip
    private String register_ip;
    //头像
    private String avatar;
    //微信Id
    private String weixin_openid;

    //身份证号
    private String idCard;
    //推广人id
    private int promoterId;
    //推广人姓名
    private String promoterName;
    //是否实名认证 1：是 2：否
    private String isReal;
    //是否推荐购买返现 0没有、1已返现
    private Integer is_return_cash;
    //首次购买金额
    private BigDecimal first_buy_money;
    //推广小程序二维码
    private String qrCode;
    //真实姓名
    private String realName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
    	this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getRegister_time() {
        return register_time;
    }

    public void setRegister_time(Date register_time) {
        this.register_time = register_time;
    }

    public Date getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(Date last_login_time) {
        this.last_login_time = last_login_time;
    }

    public String getLast_login_ip() {
        return last_login_ip;
    }

    public void setLast_login_ip(String last_login_ip) {
        this.last_login_ip = last_login_ip;
    }

    public Integer getUser_level_id() {
        return user_level_id;
    }

    public void setUser_level_id(Integer user_level_id) {
        this.user_level_id = user_level_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRegister_ip() {
        return register_ip;
    }

    public void setRegister_ip(String register_ip) {
        this.register_ip = register_ip;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getWeixin_openid() {
        return weixin_openid;
    }

    public void setWeixin_openid(String weixin_openid) {
        this.weixin_openid = weixin_openid;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public int getPromoterId() {
        return promoterId;
    }

    public void setPromoterId(int promoterId) {
        this.promoterId = promoterId;
    }

    public String getPromoterName() {
        return promoterName;
    }

    public void setPromoterName(String promoterName) {
        this.promoterName = promoterName;
    }

    public String getIsReal() {
        return isReal;
    }

    public void setIsReal(String isReal) {
        this.isReal = isReal;
    }

	public Integer getIs_return_cash() {
		return is_return_cash;
	}

	public void setIs_return_cash(Integer is_return_cash) {
		this.is_return_cash = is_return_cash;
	}

	public BigDecimal getFirst_buy_money() {
		return first_buy_money;
	}

	public void setFirst_buy_money(BigDecimal first_buy_money) {
		this.first_buy_money = first_buy_money;
	}

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
    
}
