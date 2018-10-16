package com.platform.service;

import com.platform.entity.KeywordsEntity;

import java.util.List;
import java.util.Map;

/**
 * 热闹关键词表Service接口
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-25 21:23:41
 */
public interface KeywordsService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    KeywordsEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<KeywordsEntity> queryList(Map<String, Object> map);

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
     * @param keywords 实体
     * @return 保存条数
     */
    int save(KeywordsEntity keywords);

    /**
     * 根据主键更新实体
     *
     * @param keywords 实体
     * @return 更新条数
     */
    int update(KeywordsEntity keywords);

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
