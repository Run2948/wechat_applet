package com.platform.service;

import com.platform.entity.SysSmsLogEntity;

import java.util.List;
import java.util.Map;

/**
 * 发送短信日志Service
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-12-16 23:38:05
 */
public interface SysSmsLogService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    SysSmsLogEntity queryObject(String id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<SysSmsLogEntity> queryList(Map<String, Object> map);

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
     * @param smsLog 实体
     * @return 保存条数
     */
    int save(SysSmsLogEntity smsLog);

    /**
     * 根据主键更新实体
     *
     * @param smsLog 实体
     * @return 更新条数
     */
    int update(SysSmsLogEntity smsLog);

    /**
     * 根据主键删除
     *
     * @param id
     * @return 删除条数
     */
    int delete(String id);

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return 删除条数
     */
    int deleteBatch(String[] ids);

    /**
     * 发送短信
     *
     * @param smsLog
     * @return
     */
    SysSmsLogEntity sendSms(SysSmsLogEntity smsLog);
}
