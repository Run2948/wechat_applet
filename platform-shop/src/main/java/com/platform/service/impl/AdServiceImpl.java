package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.AdDao;
import com.platform.entity.AdEntity;
import com.platform.service.AdService;

/**
 * Service实现类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-19 09:37:35
 */
@Service("adService")
public class AdServiceImpl implements AdService {
    @Autowired
    private AdDao adDao;

    @Override
    public AdEntity queryObject(Integer id) {
        return adDao.queryObject(id);
    }

    @Override
    public List<AdEntity> queryList(Map<String, Object> map) {
        return adDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return adDao.queryTotal(map);
    }

    @Override
    public int save(AdEntity ad) {
        return adDao.save(ad);
    }

    @Override
    public int update(AdEntity ad) {
        return adDao.update(ad);
    }

    @Override
    public int delete(Integer id) {
        return adDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return adDao.deleteBatch(ids);
    }
}
