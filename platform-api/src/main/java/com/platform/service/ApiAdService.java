package com.platform.service;

import com.platform.dao.ApiAdMapper;
import com.platform.entity.AdVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiAdService {
    @Autowired
    private ApiAdMapper adDao;


    public AdVo queryObject(Integer id) {
        return adDao.queryObject(id);
    }


    public List<AdVo> queryList(Map<String, Object> map) {
        return adDao.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return adDao.queryTotal(map);
    }


    public void save(AdVo brand) {
        adDao.save(brand);
    }


    public void update(AdVo brand) {
        adDao.update(brand);
    }


    public void delete(Integer id) {
        adDao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        adDao.deleteBatch(ids);
    }

}
