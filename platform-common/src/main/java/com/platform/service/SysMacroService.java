package com.platform.service;

import com.platform.entity.SysMacroEntity;

import java.util.List;
import java.util.Map;

/**
 * 通用字典表Service接口
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-22 11:48:16
 */
public interface SysMacroService {

    /**
     * 根据主键查询实体
     *
     * @param macroId 主键
     * @return 实体
     */
    SysMacroEntity queryObject(Long macroId);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<SysMacroEntity> queryList(Map<String, Object> map);

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
     * @param sysMacro 实体
     * @return 保存条数
     */
    int save(SysMacroEntity sysMacro);

    /**
     * 根据主键更新实体
     *
     * @param sysMacro 实体
     * @return 更新条数
     */
    int update(SysMacroEntity sysMacro);

    /**
     * 根据主键删除
     *
     * @param macroId
     * @return 删除条数
     */
    int delete(Long macroId);

    /**
     * 根据主键批量删除
     *
     * @param macroIds
     * @return 删除条数
     */
    int deleteBatch(Long[] macroIds);

    List<SysMacroEntity> queryMacrosByValue(String value);
}
