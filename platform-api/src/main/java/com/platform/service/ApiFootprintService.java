package com.platform.service;

import com.platform.dao.ApiFootprintMapper;
import com.platform.entity.FootprintVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiFootprintService {
    @Autowired
    private ApiFootprintMapper footprintDao;


    public FootprintVo queryObject(Integer id) {
        return footprintDao.queryObject(id);
    }


    public List<FootprintVo> queryList(Map<String, Object> map) {
        return footprintDao.queryList(map);
    }
    public List<FootprintVo> queryListFootprint(String userid) {
    	return footprintDao.queryListFootprint(userid);
    }

    public List<FootprintVo> shareList(Map<String, Object> map) {
        return footprintDao.shareList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return footprintDao.queryTotal(map);
    }


    public void save(FootprintVo footprint) {
        footprintDao.save(footprint);
    }


    public void update(FootprintVo footprint) {
        footprintDao.update(footprint);
    }


    public void delete(Integer id) {
        footprintDao.delete(id);
    }

    public void deleteByParam(Map<String, Object> map) {
        footprintDao.deleteByParam(map);
    }

    public void deleteBatch(Integer[] ids) {
        footprintDao.deleteBatch(ids);
    }

}
