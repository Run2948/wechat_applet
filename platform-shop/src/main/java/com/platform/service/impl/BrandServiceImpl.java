package com.platform.service.impl;

import com.platform.dao.BrandDao;
import com.platform.entity.BrandEntity;
import com.platform.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-19 17:59:15
 */
@Service("brandService")
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandDao brandDao;

    @Override
    public BrandEntity queryObject(Integer id) {
        return brandDao.queryObject(id);
    }

    @Override
    public List<BrandEntity> queryList(Map<String, Object> map) {
        return brandDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return brandDao.queryTotal(map);
    }

    @Override
    public int save(BrandEntity brand) {
        return brandDao.save(brand);
    }

    @Override
    public int update(BrandEntity brand) {
        return brandDao.update(brand);
    }

    @Override
    public int delete(Integer id) {
        return brandDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return brandDao.deleteBatch(ids);
    }
}
