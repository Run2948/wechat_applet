package com.platform.entity;

import java.io.Serializable;
import java.sql.Timestamp;



/**
 * 类名称：MlsUser<br> 
 * 类描述：分销用户<br>
 */
public class MlsUserEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long mlsUserId;
	private String userName;
	private String nickname;
	private String loginPassword;
	private String deviceId;
	private String userTel;
	private String profilePhoto;
	private Integer allSales;
	private Integer todaySales;
	private Integer allProfit;
	private Integer getProfit;
	private Integer fatherUserId;
	private Integer sonUserSum;
	private Timestamp ctime;
	//一级分佣
    private Integer fx1;
    //二级分佣
    private Integer fx2;
    //本人分佣
    private Integer fx;
    //平台分佣
    private Integer pfx;
	//商户id
	private Long merchantId;
    //父ID
    private Long fid;
    //分销用户根节点
    private Long rootId;

	
	public Integer getFx() {
		return fx;
	}
	public void setFx(Integer fx) {
		this.fx = fx;
	}
	public Integer getPfx() {
		return pfx;
	}
	public void setPfx(Integer pfx) {
		this.pfx = pfx;
	}
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getRootId() {
		return rootId;
	}
	public void setRootId(Long rootId) {
		this.rootId = rootId;
	}
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
	public Integer getFx1() {
		return fx1;
	}
	public void setFx1(Integer fx1) {
		this.fx1 = fx1;
	}
	public Integer getFx2() {
		return fx2;
	}
	public void setFx2(Integer fx2) {
		this.fx2 = fx2;
	}
	public Long getFid() {
		return fid;
	}
	public void setFid(Long fid) {
		this.fid = fid;
	}
	


}