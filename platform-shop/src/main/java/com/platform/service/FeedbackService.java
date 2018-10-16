package com.platform.service;

import com.platform.entity.FeedbackEntity;

import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-23 15:03:25
 */
public interface FeedbackService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    FeedbackEntity queryObject(Integer msgId);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<FeedbackEntity> queryList(Map<String, Object> map);

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
     * @param feedback 实体
     * @return 保存条数
     */
    int save(FeedbackEntity feedback);

    /**
     * 根据主键更新实体
     *
     * @param feedback 实体
     * @return 更新条数
     */
    int update(FeedbackEntity feedback);

    /**
     * 根据主键删除
     *
     * @param msgId
     * @return 删除条数
     */
    int delete(Integer msgId);

    /**
     * 根据主键批量删除
     *
     * @param msgIds
     * @return 删除条数
     */
    int deleteBatch(Integer[] msgIds);
}
