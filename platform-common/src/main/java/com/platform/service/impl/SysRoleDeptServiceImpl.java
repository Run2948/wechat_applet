package com.platform.service.impl;

import com.platform.dao.SysRoleDeptDao;
import com.platform.service.SysRoleDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 角色与部门对应关系
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017年9月18日 上午9:18:38
 */
@Service("sysRoleDeptService")
public class SysRoleDeptServiceImpl implements SysRoleDeptService {
    @Autowired
    private SysRoleDeptDao sysRoleDeptDao;

    @Override
    @Transactional
    public void saveOrUpdate(Long roleId, List<Long> deptIdList) {
        //先删除角色与菜单关系
        sysRoleDeptDao.delete(roleId);

        if (deptIdList.size() == 0) {
            return;
        }

        //保存角色与菜单关系
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", roleId);
        map.put("deptIdList", deptIdList);
        sysRoleDeptDao.save(map);
    }

    @Override
    public List<Long> queryDeptIdList(Long roleId) {
        return sysRoleDeptDao.queryDeptIdList(roleId);
    }

    @Override
    public List<Long> queryDeptIdListByUserId(Long userId) {
        return sysRoleDeptDao.queryDeptIdListByUserId(userId);
    }
}
