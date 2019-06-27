package com.platform.service;

import java.util.List;
import java.util.Map;

import com.platform.entity.MlsUserEntity2;

/**
 * Service接口
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-16 15:02:28
 */
public interface MlsUserService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
	MlsUserEntity2 queryObject(Integer id);


    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<MlsUserEntity2> queryList(Map<String, Object> map);

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
     * @param user 实体
     * @return 保存条数
     */
    int save(MlsUserEntity2 user);

    /**
     * 根据主键更新实体
     *
     * @param user 实体
     * @return 更新条数
     */
    int update(MlsUserEntity2 user);

    /**
     * 根据主键删除
     *
     * @param id
     * @return 删除条数
     */
    int delete(Integer id);


    int updatefx(MlsUserEntity2 user);
}
