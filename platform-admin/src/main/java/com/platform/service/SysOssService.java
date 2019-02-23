package com.platform.service;

import com.platform.entity.SysOssEntity;

import java.util.List;
import java.util.Map;

/**
 * 文件上传Service
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-03-25 12:13:26
 */
public interface SysOssService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    SysOssEntity queryObject(Long id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<SysOssEntity> queryList(Map<String, Object> map);

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
     * @param sysOss 实体
     * @return 保存条数
     */
    void save(SysOssEntity sysOss);

    /**
     * 根据主键更新实体
     *
     * @param sysOss 实体
     * @return 更新条数
     */
    void update(SysOssEntity sysOss);

    /**
     * 根据主键删除
     *
     * @param id
     * @return 删除条数
     */
    void delete(Long id);

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return 删除条数
     */
    void deleteBatch(Long[] ids);
    
  
}
