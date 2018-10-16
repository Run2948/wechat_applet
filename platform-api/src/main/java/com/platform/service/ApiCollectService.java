package com.platform.service;

import com.platform.dao.ApiCollectMapper;
import com.platform.entity.CollectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiCollectService {
    @Autowired
    private ApiCollectMapper collectDao;


    public CollectVo queryObject(Integer id) {
        return collectDao.queryObject(id);
    }


    public List<CollectVo> queryList(Map<String, Object> map) {
        return collectDao.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return collectDao.queryTotal(map);
    }


    public int save(CollectVo collect) {
        return collectDao.save(collect);
    }


    public void update(CollectVo collect) {
        collectDao.update(collect);
    }


    public int delete(Integer id) {
        return collectDao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        collectDao.deleteBatch(ids);
    }

}
