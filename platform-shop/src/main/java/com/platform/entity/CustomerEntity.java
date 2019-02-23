package com.platform.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 客户实体
 */
public class CustomerEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //会员姓名
    private String uname;
    //客户性别 0:未知性别 1：男 2：女
    private String gender;
    //出生日期
    private Date birthday;
    //手机号码
    private String mobile;
    //邮寄地址用户
    private int addressUserId;
    //维护状态 0：未维护 1:已维护
    private String upkeepState;
    //客户属性 0:默认用户 1：准客户
    private String customerState;
    //备注
    private String remarks;

    //创建时间
    private Date ctime;
    //创建人id
    private Integer uid;
    //工作
    private String job;
    private String end_date;
    private String realName;
    //手机号码
    private String mobile2;
    //收货地址
    private AddressEntity addressEntity;
    //客户维护历史
    private List<UpkeepEntity> upkeepEntity;

    public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobile2() {
		return mobile2;
	}

	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getAddressUserId() {
        return addressUserId;
    }

    public void setAddressUserId(int addressUserId) {
        this.addressUserId = addressUserId;
    }

    public String getUpkeepState() {
        return upkeepState;
    }

    public void setUpkeepState(String upkeepState) {
        this.upkeepState = upkeepState;
    }

    public String getCustomerState() {
        return customerState;
    }

    public void setCustomerState(String customerState) {
        this.customerState = customerState;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public AddressEntity getAddressEntity() {
        return addressEntity;
    }

    public void setAddressEntity(AddressEntity addressEntity) {
        this.addressEntity = addressEntity;
    }

    public List<UpkeepEntity> getUpkeepEntity() {
        return upkeepEntity;
    }

    public void setUpkeepEntity(List<UpkeepEntity> upkeepEntity) {
        this.upkeepEntity = upkeepEntity;
    }
}
