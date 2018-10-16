package com.platform.service;

import com.platform.dao.ApiFeedbackMapper;
import com.platform.entity.FeedbackVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiFeedbackService {
    @Autowired
    private ApiFeedbackMapper feedbackMapper;


    public FeedbackVo queryObject(Integer id) {
        return feedbackMapper.queryObject(id);
    }


    public List<FeedbackVo> queryList(Map<String, Object> map) {
        return feedbackMapper.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return feedbackMapper.queryTotal(map);
    }


    public void save(FeedbackVo feedbackVo) {
        feedbackMapper.save(feedbackVo);
    }


    public void update(FeedbackVo feedbackVo) {
        feedbackMapper.update(feedbackVo);
    }


    public void delete(Integer id) {
        feedbackMapper.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        feedbackMapper.deleteBatch(ids);
    }

}
