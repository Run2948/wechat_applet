package com.platform.service;

import com.platform.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-21 15:32:31
 */
public interface CategoryService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    CategoryEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<CategoryEntity> queryList(Map<String, Object> map);

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
     * @param category 实体
     * @return 保存条数
     */
    int save(CategoryEntity category);

    /**
     * 根据主键更新实体
     *
     * @param category 实体
     * @return 更新条数
     */
    int update(CategoryEntity category);

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
