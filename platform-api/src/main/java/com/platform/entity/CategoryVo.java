package com.platform.entity;

import java.io.Serializable;
import java.util.List;


/**
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-15 08:03:39
 */
public class CategoryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //分类名称
    private String name;
    //关键字
    private String keywords;
    //描述
    private String front_desc;
    //父节点
    private Integer parent_id;
    //排序
    private Integer sort_order;
    //首页展示
    private Integer show_index;
    //显示
    private Integer is_show;
    //banner图片
    private String banner_url;
    //icon链接
    private String icon_url;
    //图片
    private String img_url;
    //手机banner
    private String wap_banner_url;
    //级别
    private String level;
    //类型
    private Integer type;
    //
    private String front_name;

    private Boolean checked;

    private List<CategoryVo> subCategoryList;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getFront_desc() {
        return front_desc;
    }

    public void setFront_desc(String front_desc) {
        this.front_desc = front_desc;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public Integer getSort_order() {
        return sort_order;
    }

    public void setSort_order(Integer sort_order) {
        this.sort_order = sort_order;
    }

    public Integer getShow_index() {
        return show_index;
    }

    public void setShow_index(Integer show_index) {
        this.show_index = show_index;
    }

    public Integer getIs_show() {
        return is_show;
    }

    public void setIs_show(Integer is_show) {
        this.is_show = is_show;
    }

    public String getBanner_url() {
        return banner_url;
    }

    public void setBanner_url(String banner_url) {
        this.banner_url = banner_url;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getWap_banner_url() {
        return wap_banner_url;
    }

    public void setWap_banner_url(String wap_banner_url) {
        this.wap_banner_url = wap_banner_url;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFront_name() {
        return front_name;
    }

    public void setFront_name(String front_name) {
        this.front_name = front_name;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public List<CategoryVo> getSubCategoryList() {
        return subCategoryList;
    }

    public void setSubCategoryList(List<CategoryVo> subCategoryList) {
        this.subCategoryList = subCategoryList;
    }
}
