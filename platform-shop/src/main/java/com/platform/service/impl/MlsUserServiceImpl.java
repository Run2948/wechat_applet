package com.platform.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.dao.MlsUserDao;
import com.platform.entity.MlsUserEntity2;
import com.platform.service.MlsUserService;

/**
 * Service实现类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-16 15:02:28
 */
@Service("mlsUserService")
public class MlsUserServiceImpl implements MlsUserService {
    @Autowired
    private MlsUserDao mlsUserDao;

    @Override
    public MlsUserEntity2 queryObject(Integer id) {
        return mlsUserDao.queryObject(id);
    }

    @Override
    public List<MlsUserEntity2> queryList(Map<String, Object> map) {
        return mlsUserDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return mlsUserDao.queryTotal(map);
    }

    @Override
    public int updatefx(MlsUserEntity2 user) {
        return mlsUserDao.updatefx(user);
    }

	@Override
	public int save(MlsUserEntity2 user) {
		return 0;
	}

	@Override
	public int update(MlsUserEntity2 user) {
		return 0;
	}

	@Override
	public int delete(Integer id) {
		return 0;
	}
}
