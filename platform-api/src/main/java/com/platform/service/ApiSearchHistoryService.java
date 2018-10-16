package com.platform.service;

import com.platform.dao.ApiSearchHistoryMapper;
import com.platform.entity.SearchHistoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiSearchHistoryService {
    @Autowired
    private ApiSearchHistoryMapper searchHistoryDao;


    public SearchHistoryVo queryObject(Integer id) {
        return searchHistoryDao.queryObject(id);
    }


    public List<SearchHistoryVo> queryList(Map<String, Object> map) {
        return searchHistoryDao.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return searchHistoryDao.queryTotal(map);
    }


    public void save(SearchHistoryVo region) {
        searchHistoryDao.save(region);
    }


    public void update(SearchHistoryVo region) {
        searchHistoryDao.update(region);
    }


    public void delete(Integer id) {
        searchHistoryDao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        searchHistoryDao.deleteBatch(ids);
    }

    public void deleteByUserId(Long userId) {
        searchHistoryDao.deleteByUserId(userId);
    }
}
