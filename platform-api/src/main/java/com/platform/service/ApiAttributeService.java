package com.platform.service;

import com.platform.dao.ApiAttributeMapper;
import com.platform.entity.AttributeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiAttributeService {
    @Autowired
    private ApiAttributeMapper attributeMapper;


    public AttributeVo queryObject(Integer id) {
        return attributeMapper.queryObject(id);
    }


    public List<AttributeVo> queryList(Map<String, Object> map) {
        return attributeMapper.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return attributeMapper.queryTotal(map);
    }


    public void save(AttributeVo goods) {
        attributeMapper.save(goods);
    }


    public void update(AttributeVo goods) {
        attributeMapper.update(goods);
    }


    public void delete(Integer id) {
        attributeMapper.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        attributeMapper.deleteBatch(ids);
    }

}
