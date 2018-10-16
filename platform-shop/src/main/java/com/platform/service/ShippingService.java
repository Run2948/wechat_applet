package com.platform.service;

import com.platform.entity.ShippingEntity;

import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-09-04 21:42:24
 */
public interface ShippingService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    ShippingEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<ShippingEntity> queryList(Map<String, Object> map);

    /**
     * 分页统计总数
     *
     * @param map 参数
     * @return 总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存实体
     *
     * @param shipping 实体
     * @return 保存条数
     */
    int save(ShippingEntity shipping);

    /**
     * 根据主键更新实体
     *
     * @param shipping 实体
     * @return 更新条数
     */
    int update(ShippingEntity shipping);

    /**
     * 根据主键删除
     *
     * @param id
     * @return 删除条数
     */
    int delete(Integer id);

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return 删除条数
     */
    int deleteBatch(Integer[] ids);
}
