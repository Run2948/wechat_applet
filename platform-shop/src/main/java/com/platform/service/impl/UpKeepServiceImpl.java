package com.platform.service.impl;

import com.platform.dao.UpkeepDao;
import com.platform.entity.UpkeepEntity;
import com.platform.service.UpkeepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 客户维护历史实现类
 */
@Service("upKeepService")
public class UpKeepServiceImpl implements UpkeepService {
    @Autowired
    private UpkeepDao upkeepDao;


    @Override
    public UpkeepEntity queryObject(Integer id) {
        return upkeepDao.queryObject(id);
    }

    @Override
    public List<UpkeepEntity> queryList(Map<String, Object> map) {
        return upkeepDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return upkeepDao.queryTotal(map);
    }

    @Override
    public int save(UpkeepEntity upkeep) {
        return upkeepDao.save(upkeep);
    }

    @Override
    public int update(UpkeepEntity upkeep) {
        return upkeepDao.update(upkeep);
    }

    @Override
    public int delete(Integer id) {
        return upkeepDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return 0;
    }
}
