package com.platform.service;

import com.platform.dao.GroupBuyingMapper;
import com.platform.entity.GroupBuyingVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class GroupBuyingService {
    @Autowired
    private GroupBuyingMapper dao;


    public GroupBuyingVo queryObject(String id) {
        return dao.queryObject(id);
    }


    public List<GroupBuyingVo> queryList(Map<String, Object> map) {
        return dao.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return dao.queryTotal(map);
    }


    public void save(GroupBuyingVo brand) {
        dao.save(brand);
    }


    public void update(GroupBuyingVo brand) {
        dao.update(brand);
    }


    public void delete(Integer id) {
        dao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        dao.deleteBatch(ids);
    }


    public List<GroupBuyingVo> queryLoseList(Map map) {
        return dao.queryLoseList(map);
    }
}
