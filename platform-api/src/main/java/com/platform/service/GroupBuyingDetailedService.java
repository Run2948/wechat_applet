package com.platform.service;

import com.platform.dao.GroupBuyingDetailedMapper;
import com.platform.entity.GroupBuyingDetailedVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class GroupBuyingDetailedService {
    @Autowired
    private GroupBuyingDetailedMapper adDao;


    public GroupBuyingDetailedVo queryObject(Integer id) {
        return adDao.queryObject(id);
    }


    public List<GroupBuyingDetailedVo> queryList(Map<String, Object> map) {
        return adDao.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return adDao.queryTotal(map);
    }


    public void save(GroupBuyingDetailedVo brand) {
        adDao.save(brand);
    }


    public void update(GroupBuyingDetailedVo brand) {
        adDao.update(brand);
    }


    public void delete(Integer id) {
        adDao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        adDao.deleteBatch(ids);
    }

}
