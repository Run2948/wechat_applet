package com.platform.service;

import com.platform.entity.CustomerEntity;

import java.util.List;
import java.util.Map;

/**
 * 客户信息维护
 */
public interface CustomerService {

    /**
     * 根据主键查询实体
     *
     * @param customer 主键
     * @return 实体
     */
    CustomerEntity queryObject(CustomerEntity customer );
    CustomerEntity queryObject(Integer id);
    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<CustomerEntity> queryList(Map<String, Object> map);

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
     * @param customer 实体
     * @return 保存条数
     */
    int save(CustomerEntity customer);

    /**
     * 根据主键更新实体
     *
     * @param customer 实体
     * @return 更新条数
     */
    int update(CustomerEntity customer);

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
