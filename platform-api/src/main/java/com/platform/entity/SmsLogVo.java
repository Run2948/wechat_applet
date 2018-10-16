package com.platform.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 实体表名 nideshop_sms_log
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-25 10:04:52
 */
public class SmsLogVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    //
    private Long user_id;
    //
    private String phone;
    //
    private Long log_date;
    // 发送模板
    private String sms_code;
    // 1成功 0失败
    private Integer send_status;
    //
    private String sms_text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getLog_date() {
        return log_date;
    }

    public void setLog_date(Long log_date) {
        this.log_date = log_date;
    }

    public String getSms_code() {
        return sms_code;
    }

    public void setSms_code(String sms_code) {
        this.sms_code = sms_code;
    }

    public Integer getSend_status() {
        return send_status;
    }

    public void setSend_status(Integer send_status) {
        this.send_status = send_status;
    }

    public String getSms_text() {
        return sms_text;
    }

    public void setSms_text(String sms_text) {
        this.sms_text = sms_text;
    }
}
