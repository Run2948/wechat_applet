package com.platform.service;

import com.platform.dao.ApiGoodsGalleryMapper;
import com.platform.entity.GoodsGalleryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiGoodsGalleryService {
    @Autowired
    private ApiGoodsGalleryMapper goodsGalleryDao;


    public GoodsGalleryVo queryObject(Integer id) {
        return goodsGalleryDao.queryObject(id);
    }


    public List<GoodsGalleryVo> queryList(Map<String, Object> map) {
        return goodsGalleryDao.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return goodsGalleryDao.queryTotal(map);
    }


    public void save(GoodsGalleryVo goods) {
        goodsGalleryDao.save(goods);
    }


    public void update(GoodsGalleryVo goods) {
        goodsGalleryDao.update(goods);
    }


    public void delete(Integer id) {
        goodsGalleryDao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        goodsGalleryDao.deleteBatch(ids);
    }

}
