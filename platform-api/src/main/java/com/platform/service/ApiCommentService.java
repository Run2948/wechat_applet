package com.platform.service;

import com.platform.dao.ApiCommentMapper;
import com.platform.entity.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiCommentService {
    @Autowired
    private ApiCommentMapper commentDao;


    public CommentVo queryObject(Integer id) {
        return commentDao.queryObject(id);
    }


    public List<CommentVo> queryList(Map<String, Object> map) {
        return commentDao.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return commentDao.queryTotal(map);
    }

    public int queryhasPicTotal(Map<String, Object> map) {
        return commentDao.queryhasPicTotal(map);
    }

    public int save(CommentVo comment) {
        return commentDao.save(comment);
    }


    public void update(CommentVo comment) {
        commentDao.update(comment);
    }


    public void delete(Integer id) {
        commentDao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        commentDao.deleteBatch(ids);
    }

}
