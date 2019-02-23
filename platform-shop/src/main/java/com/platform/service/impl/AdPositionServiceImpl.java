package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.AdPositionDao;
import com.platform.entity.AdPositionEntity;
import com.platform.service.AdPositionService;

/**
 * Service实现类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-19 12:02:42
 */
@Service("adPositionService")
public class AdPositionServiceImpl implements AdPositionService {
    @Autowired
    private AdPositionDao adPositionDao;

    @Override
    public AdPositionEntity queryObject(Integer id) {
        return adPositionDao.queryObject(id);
    }

    @Override
    public List<AdPositionEntity> queryList(Map<String, Object> map) {
        return adPositionDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return adPositionDao.queryTotal(map);
    }

    @Override
    public int save(AdPositionEntity adPosition) {
        return adPositionDao.save(adPosition);
    }

    @Override
    public int update(AdPositionEntity adPosition) {
        return adPositionDao.update(adPosition);
    }

    @Override
    public int delete(Integer id) {
        return adPositionDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return adPositionDao.deleteBatch(ids);
    }
}
