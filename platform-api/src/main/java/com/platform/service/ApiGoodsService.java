package com.platform.service;

import com.platform.dao.ApiGoodsMapper;
import com.platform.entity.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiGoodsService {
    @Autowired
    private ApiGoodsMapper goodsDao;


    public GoodsVo queryObject(Integer id) {
        return goodsDao.queryObject(id);
    }


    public List<GoodsVo> queryList(Map<String, Object> map) {
        return goodsDao.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return goodsDao.queryTotal(map);
    }


    public void save(GoodsVo goods) {
        goodsDao.save(goods);
    }


    public void update(GoodsVo goods) {
        goodsDao.update(goods);
    }


    public void delete(Integer id) {
        goodsDao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        goodsDao.deleteBatch(ids);
    }

    public List<GoodsVo> queryHotGoodsList(Map<String, Object> map) {
        return goodsDao.queryHotGoodsList(map);
    }

    public List<GoodsVo> queryCatalogProductList(Map<String, Object> map) {
        return goodsDao.queryCatalogProductList(map);
    }
}
