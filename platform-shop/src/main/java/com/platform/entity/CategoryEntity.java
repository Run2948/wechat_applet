package com.platform.entity;

/**
 * 实体
 * 表名 nideshop_category
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-21 15:32:31
 */
public class CategoryEntity extends Tree<CategoryEntity> {

    //主键
    private Integer id;
    //分类名称
    private String name;
    //关键字
    private String keywords;
    //描述
    private String frontDesc;
    //父节点
    private Integer parentId;
    //排序
    private Integer sortOrder;
    //首页展示
    private Integer showIndex;
    //显示
    private Integer isShow;
    //banner图片
    private String bannerUrl;
    //icon链接
    private String iconUrl;
    //图片
    private String imgUrl;
    //手机banner
    private String wapBannerUrl;
    //级别
    private String level;
    //类型
    private Integer type;
    //
    private String frontName;

    //翻译用字段
    private String show;

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

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
     * 设置：分类名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：分类名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：关键字
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * 获取：关键字
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * 设置：描述
     */
    public void setFrontDesc(String frontDesc) {
        this.frontDesc = frontDesc;
    }

    /**
     * 获取：描述
     */
    public String getFrontDesc() {
        return frontDesc;
    }

    /**
     * 设置：父节点
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：父节点
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置：排序
     */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * 获取：排序
     */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /**
     * 设置：首页展示
     */
    public void setShowIndex(Integer showIndex) {
        this.showIndex = showIndex;
    }

    /**
     * 获取：首页展示
     */
    public Integer getShowIndex() {
        return showIndex;
    }

    /**
     * 设置：显示
     */
    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    /**
     * 获取：显示
     */
    public Integer getIsShow() {
        return isShow;
    }

    /**
     * 设置：banner图片
     */
    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    /**
     * 获取：banner图片
     */
    public String getBannerUrl() {
        return bannerUrl;
    }

    /**
     * 设置：icon链接
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    /**
     * 获取：icon链接
     */
    public String getIconUrl() {
        return iconUrl;
    }

    /**
     * 设置：图片
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * 获取：图片
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置：手机banner
     */
    public void setWapBannerUrl(String wapBannerUrl) {
        this.wapBannerUrl = wapBannerUrl;
    }

    /**
     * 获取：手机banner
     */
    public String getWapBannerUrl() {
        return wapBannerUrl;
    }

    /**
     * 设置：级别
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * 获取：级别
     */
    public String getLevel() {
        return level;
    }

    /**
     * 设置：类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取：类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置：
     */
    public void setFrontName(String frontName) {
        this.frontName = frontName;
    }

    /**
     * 获取：
     */
    public String getFrontName() {
        return frontName;
    }
}
