package com.platform.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 热闹关键词表
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-15 08:03:39
 */
public class KeywordsVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //关键字
    private String keyword;
    //热销
    private Integer is_hot;
    //默认
    private Integer is_default;
    //显示
    private Integer is_show;
    //排序
    private Integer sort_order;
    //关键词的跳转链接
    private String scheme_url;
    //主键
    private Integer id;
    //类型
    private Integer type;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getIs_hot() {
        return is_hot;
    }

    public void setIs_hot(Integer is_hot) {
        this.is_hot = is_hot;
    }

    public Integer getIs_default() {
        return is_default;
    }

    public void setIs_default(Integer is_default) {
        this.is_default = is_default;
    }

    public Integer getIs_show() {
        return is_show;
    }

    public void setIs_show(Integer is_show) {
        this.is_show = is_show;
    }

    public Integer getSort_order() {
        return sort_order;
    }

    public void setSort_order(Integer sort_order) {
        this.sort_order = sort_order;
    }

    public String getScheme_url() {
        return scheme_url;
    }

    public void setScheme_url(String scheme_url) {
        this.scheme_url = scheme_url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
