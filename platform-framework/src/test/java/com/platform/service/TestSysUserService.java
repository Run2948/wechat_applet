package com.platform.service;

import com.platform.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * Service测试接口
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-07-09 10:16:22
 */
public interface TestSysUserService {
    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    SysUserEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<SysUserEntity> queryList(Map<String, Object> map);
}
