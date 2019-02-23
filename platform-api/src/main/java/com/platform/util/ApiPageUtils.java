package com.platform.util;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年11月4日 下午12:59:00
 */
public class ApiPageUtils implements Serializable {
    private static final long serialVersionUID = 1L;
    //总记录数
    private int count;
    //每页记录数
    private int numsPerPage;
    //总页数
    private int totalPages;
    //当前页数
    private int currentPage;
    //列表数据
    private List<?> data;
    //扩展
    private Object filterCategory;
    //扩展
    private Object goodsList;

    public ApiPageUtils(){

    }
    /**
     * 分页
     *
     * @param list        列表数据
     * @param count       总记录数
     * @param numsPerPage 每页记录数
     * @param currentPage 当前页数
     */
    public ApiPageUtils(List<?> list, int count, int numsPerPage, int currentPage) {
        this.data = list;
        this.count = count;
        this.numsPerPage = numsPerPage;
        this.currentPage = currentPage;
        this.totalPages = (int) Math.ceil((double) count / numsPerPage);
    }

    public ApiPageUtils(PageInfo pageInfo) {
        this.count = (int) pageInfo.getTotal();
        this.numsPerPage = pageInfo.getPageSize();
        this.currentPage = pageInfo.getPageNum();
        this.totalPages = pageInfo.getPages();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getNumsPerPage() {
        return numsPerPage;
    }

    public void setNumsPerPage(int numsPerPage) {
        this.numsPerPage = numsPerPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public Object getFilterCategory() {
        return filterCategory;
    }

    public void setFilterCategory(Object filterCategory) {
        this.filterCategory = filterCategory;
    }

    public Object getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(Object goodsList) {
        this.goodsList = goodsList;
    }
}
