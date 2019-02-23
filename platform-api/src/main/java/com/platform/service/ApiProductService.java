package com.platform.service;

import com.platform.dao.ApiProductMapper;
import com.platform.entity.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiProductService {
    @Autowired
    private ApiProductMapper productDao;


    public ProductVo queryObject(Integer id) {
        return productDao.queryObject(id);
    }


    public List<ProductVo> queryList(Map<String, Object> map) {
        return productDao.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return productDao.queryTotal(map);
    }


    public void save(ProductVo goods) {
        productDao.save(goods);
    }


    public void update(ProductVo goods) {
        productDao.update(goods);
    }


    public void delete(Integer id) {
        productDao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        productDao.deleteBatch(ids);
    }

}
