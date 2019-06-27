package com.platform.dao;

import java.util.List;
import java.util.Map;

import com.platform.entity.MlsUserEntity2;
import com.platform.entity.SysUserEntity;
import com.platform.entity.UserWindowDto;

/**
 * 系统用户
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年9月18日 上午9:34:11
 */
public interface SysUserDao extends BaseDao<SysUserEntity> {

    /**
     * 查询用户的所有权限
     *
     * @param userId 用户ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 根据用户名，查询系统用户
     */
    SysUserEntity queryByUserName(String username);

    /**
     * 修改密码
     */
    int updatePassword(Map<String, Object> map);
    /**
     * 根据实体类查询
     * @param userWindowDto
     * @return
     */
    List<UserWindowDto> queryListByBean(UserWindowDto userWindowDto);
    /**
       *更新商户ID
     */
    int updateMerchantId(SysUserEntity t);
    
    int mlsUseCount(String tel);
    void insertMlsUse(MlsUserEntity2 t);
    void updateMlsUse(MlsUserEntity2 t);
}

