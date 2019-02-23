package com.platform.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：MlsUser<br> 
 * 类描述：分销用户<br>
 */
@ApiModel(value="分销用户")
public class MlsUserVo implements Serializable{
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="分销用户ID")
	private Long mlsUserId;
	@ApiModelProperty(value="用户名")
	private String userName;
	@ApiModelProperty(value="昵称")
	private String nickname;
	@ApiModelProperty(value="登录密码-用户Id")
	private String loginPassword;
	@ApiModelProperty(value="设备编号")
	private String deviceId;
	@ApiModelProperty(value="电话")
	private String userTel;
	@ApiModelProperty(value="头像")
	private String profilePhoto;
	@ApiModelProperty(value="总销售额-单位分")
	private Integer allSales;
	@ApiModelProperty(value="今天销售额-单位分")
	private Integer todaySales;
	@ApiModelProperty(value="总分润-单位分")
	private Integer allProfit;
	@ApiModelProperty(value="可提现分润-单位分")
	private Integer getProfit;
	@ApiModelProperty(value="父用户ID")
	private Integer fatherUserId;
	@ApiModelProperty(value="子用户数量")
	private Integer sonUserSum;
	@ApiModelProperty(value="创建时间")
	private Timestamp ctime;

	/**
	 * 设置:分销用户ID
	 */
	public void setMlsUserId(Long value) {
		this.mlsUserId = value;
	}
	/**
	 * 获取:分销用户ID
	 */
	public Long getMlsUserId() {
		return mlsUserId;
	}

	/**
	 * 设置:用户名
	 */
	public void setUserName(String value) {
		this.userName = value;
	}
	/**
	 * 获取:用户名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置:昵称
	 */
	public void setNickname(String value) {
		this.nickname = value;
	}
	/**
	 * 获取:昵称
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 设置:登录密码-用户Id
	 */
	public void setLoginPassword(String value) {
		this.loginPassword = value;
	}
	/**
	 * 获取:登录密码-用户Id
	 */
	public String getLoginPassword() {
		return loginPassword;
	}

	/**
	 * 设置:设备编号
	 */
	public void setDeviceId(String value) {
		this.deviceId = value;
	}
	/**
	 * 获取:设备编号
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * 设置:电话
	 */
	public void setUserTel(String value) {
		this.userTel = value;
	}
	/**
	 * 获取:电话
	 */
	public String getUserTel() {
		return userTel;
	}

	/**
	 * 设置:头像
	 */
	public void setProfilePhoto(String value) {
		this.profilePhoto = value;
	}
	/**
	 * 获取:头像
	 */
	public String getProfilePhoto() {
		return profilePhoto;
	}

	/**
	 * 设置:总销售额-单位分
	 */
	public void setAllSales(Integer value) {
		this.allSales = value;
	}
	/**
	 * 获取:总销售额-单位分
	 */
	public Integer getAllSales() {
		return allSales;
	}

	/**
	 * 设置:今天销售额-单位分
	 */
	public void setTodaySales(Integer value) {
		this.todaySales = value;
	}
	/**
	 * 获取:今天销售额-单位分
	 */
	public Integer getTodaySales() {
		return todaySales;
	}

	/**
	 * 设置:总分润-单位分
	 */
	public void setAllProfit(Integer value) {
		this.allProfit = value;
	}
	/**
	 * 获取:总分润-单位分
	 */
	public Integer getAllProfit() {
		return allProfit;
	}

	/**
	 * 设置:可提现分润-单位分
	 */
	public void setGetProfit(Integer value) {
		this.getProfit = value;
	}
	/**
	 * 获取:可提现分润-单位分
	 */
	public Integer getGetProfit() {
		return getProfit;
	}

	/**
	 * 设置:父用户ID
	 */
	public void setFatherUserId(Integer value) {
		this.fatherUserId = value;
	}
	/**
	 * 获取:父用户ID
	 */
	public Integer getFatherUserId() {
		return fatherUserId;
	}

	/**
	 * 设置:子用户数量
	 */
	public void setSonUserSum(Integer value) {
		this.sonUserSum = value;
	}
	/**
	 * 获取:子用户数量
	 */
	public Integer getSonUserSum() {
		return sonUserSum;
	}

	/**
	 * 设置:创建时间
	 */
	public void setCtime(Timestamp value) {
		this.ctime = value;
	}
	/**
	 * 获取:创建时间
	 */
	public Timestamp getCtime() {
		return ctime;
	}


}