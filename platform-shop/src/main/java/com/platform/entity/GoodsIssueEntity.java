package com.platform.entity;

import java.io.Serializable;


/**
 * 实体
 * 表名 nideshop_goods_issue
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-23 14:12:34
 */
public class GoodsIssueEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //问题
    private String question;
    //回答
    private String answer;

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
     * 设置：问题
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * 获取：问题
     */
    public String getQuestion() {
        return question;
    }

    /**
     * 设置：回答
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * 获取：回答
     */
    public String getAnswer() {
        return answer;
    }
}
