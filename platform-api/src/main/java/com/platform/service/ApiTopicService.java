package com.platform.service;

import com.platform.dao.ApiTopicMapper;
import com.platform.entity.TopicVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiTopicService {
    @Autowired
    private ApiTopicMapper topicDao;


    public TopicVo queryObject(Integer id) {
        return topicDao.queryObject(id);
    }


    public List<TopicVo> queryList(Map<String, Object> map) {
        return topicDao.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return topicDao.queryTotal(map);
    }


    public void save(TopicVo topic) {
        topicDao.save(topic);
    }


    public void update(TopicVo topic) {
        topicDao.update(topic);
    }


    public void delete(Integer id) {
        topicDao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        topicDao.deleteBatch(ids);
    }

}
