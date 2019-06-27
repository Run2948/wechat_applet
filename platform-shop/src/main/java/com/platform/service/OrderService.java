package com.platform.service;

import java.util.List;
import java.util.Map;

import com.platform.entity.GroupBuyingEntity;
import com.platform.entity.OrderEntity;

/**
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-13 10:41:09
 */
public interface OrderService {	

    OrderEntity queryObject(Integer id);

    List<OrderEntity> queryList(Map<String, Object> map);
    List<GroupBuyingEntity> queryGroupList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);
    int queryGroupTotal(Map<String, Object> map);

    int save(OrderEntity order);

    int update(OrderEntity order);

    int delete(Integer id);

    int deleteBatch(Integer[] ids);

    /**
     * 确定收货
     *
     * @param id
     * @return
     */
    int confirm(Integer id);

    int sendGoods(OrderEntity order);
}
