package com.platform.service;

import com.platform.dao.ApiGoodsIssueMapper;
import com.platform.entity.GoodsIssueVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiGoodsIssueService {
    @Autowired
    private ApiGoodsIssueMapper goodsIssueDao;


    public GoodsIssueVo queryObject(Integer id) {
        return goodsIssueDao.queryObject(id);
    }


    public List<GoodsIssueVo> queryList(Map<String, Object> map) {
        return goodsIssueDao.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return goodsIssueDao.queryTotal(map);
    }


    public void save(GoodsIssueVo goods) {
        goodsIssueDao.save(goods);
    }


    public void update(GoodsIssueVo goods) {
        goodsIssueDao.update(goods);
    }


    public void delete(Integer id) {
        goodsIssueDao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        goodsIssueDao.deleteBatch(ids);
    }

}
