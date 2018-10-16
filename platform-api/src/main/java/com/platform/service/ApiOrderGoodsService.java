package com.platform.service;

import com.platform.dao.ApiOrderGoodsMapper;
import com.platform.entity.OrderGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiOrderGoodsService {
    @Autowired
    private ApiOrderGoodsMapper orderGoodsDao;


    public OrderGoodsVo queryObject(Integer id) {
        return orderGoodsDao.queryObject(id);
    }


    public List<OrderGoodsVo> queryList(Map<String, Object> map) {
        return orderGoodsDao.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return orderGoodsDao.queryTotal(map);
    }


    public void save(OrderGoodsVo order) {
        orderGoodsDao.save(order);
    }


    public void update(OrderGoodsVo order) {
        orderGoodsDao.update(order);
    }


    public void delete(Integer id) {
        orderGoodsDao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        orderGoodsDao.deleteBatch(ids);
    }

}
