package com.platform.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 会员实体
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-16 15:02:28
 */
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //会员名称
    private String username;
    //会员密码
    private String password;
    //性别
    private Integer gender;
    //出生日期
    private Date birthday;
    //注册时间
    private Date registerTime;
    //最后登录时间
    private Date lastLoginTime;
    //最后登录Ip
    private String lastLoginIp;
    //会员等级
    private Integer userLevelId;
    //微信名
    private String nickname;
    //手机号码
    private String mobile;
    //注册Ip
    private String registerIp;
    //头像
    private String avatar;
    //微信Id
    private String weixinOpenid;

    /**
     * 翻译用的字段
     */
    //会员级别
    private String levelName;

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
     * 设置：会员名称
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取：会员名称
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置：会员密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取：会员密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置：性别
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * 获取：性别
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * 设置：出生日期
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取：出生日期
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置：注册时间
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * 获取：注册时间
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * 设置：最后登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取：最后登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置：最后登录Ip
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    /**
     * 获取：最后登录Ip
     */
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    /**
     * 设置：会员等级
     */
    public void setUserLevelId(Integer userLevelId) {
        this.userLevelId = userLevelId;
    }

    /**
     * 获取：会员等级
     */
    public Integer getUserLevelId() {
        return userLevelId;
    }

    /**
     * 设置：别名
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取：别名
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置：手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：手机号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置：注册Ip
     */
    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    /**
     * 获取：注册Ip
     */
    public String getRegisterIp() {
        return registerIp;
    }

    /**
     * 设置：化名
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取：化名
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置：微信Id
     */
    public void setWeixinOpenid(String weixinOpenid) {
        this.weixinOpenid = weixinOpenid;
    }

    /**
     * 获取：微信Id
     */
    public String getWeixinOpenid() {
        return weixinOpenid;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
}
