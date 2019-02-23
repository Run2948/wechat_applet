package com.platform.service;

import com.platform.dao.ApiGoodsSpecificationMapper;
import com.platform.entity.GoodsSpecificationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiGoodsSpecificationService {
    @Autowired
    private ApiGoodsSpecificationMapper goodsDao;


    public GoodsSpecificationVo queryObject(Integer id) {
        return goodsDao.queryObject(id);
    }


    public List<GoodsSpecificationVo> queryList(Map<String, Object> map) {
        return goodsDao.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return goodsDao.queryTotal(map);
    }


    public void save(GoodsSpecificationVo goods) {
        goodsDao.save(goods);
    }


    public void update(GoodsSpecificationVo goods) {
        goodsDao.update(goods);
    }


    public void delete(Integer id) {
        goodsDao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        goodsDao.deleteBatch(ids);
    }

}
