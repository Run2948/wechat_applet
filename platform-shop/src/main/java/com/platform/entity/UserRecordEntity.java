package com.platform.entity;


import java.io.Serializable;


/**
 * 类名称：UserRecord<br> 
 * 类描述：<br>
 */
public class UserRecordEntity  implements Serializable{
	private static final long serialVersionUID = 1L;

	//reportId
	private Integer reportId;
	//分销用户ID
	private Long mlsUserId;
	//记录类型：1提现、2交易分成
	private Integer types;
	//记录类型内容
	private String typesStr;
	//金额单位分
	private Integer price;
	//备注
	private String remarks;
	private String userName;
	//创建时间
	private String ctime;
	private Integer page;
	private Integer size;
	private Integer offset;
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	/**
	 * 设置:reportId
	 */
	public void setReportId(Integer value) {
		this.reportId = value;
	}
	/**
	 * 获取:reportId
	 */
	public Integer getReportId() {
		return reportId;
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
	 * 设置:记录类型：1提现、2交易分成
	 */
	public void setTypes(Integer value) {
		this.types = value;
	}
	/**
	 * 获取:记录类型：1提现、2交易分成
	 */
	public Integer getTypes() {
		return types;
	}

	/**
	 * 设置:记录类型内容
	 */
	public void setTypesStr(String value) {
		this.typesStr = value;
	}
	/**
	 * 获取:记录类型内容
	 */
	public String getTypesStr() {
		return typesStr;
	}

	/**
	 * 设置:金额单位分
	 */
	public void setPrice(Integer value) {
		this.price = value;
	}
	/**
	 * 获取:金额单位分
	 */
	public Integer getPrice() {
		return price;
	}

	/**
	 * 设置:备注
	 */
	public void setRemarks(String value) {
		this.remarks = value;
	}
	/**
	 * 获取:备注
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * 设置:创建时间
	 */
	public void setCtime(String value) {
		this.ctime = value;
	}
	/**
	 * 获取:创建时间
	 */
	public String getCtime() {
		return ctime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}