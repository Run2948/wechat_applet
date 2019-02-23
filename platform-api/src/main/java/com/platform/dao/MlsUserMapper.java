package com.platform.dao;

import com.platform.entity.MlsUserVo;
import com.platform.util.EntityDao;
/**
 * 分销用户<br>
 */
public interface MlsUserMapper extends EntityDao<MlsUserVo,Long>{
	public MlsUserVo findByDeviceId(String  deviceId) ;
	public MlsUserVo findByUserTel(String  userTel) ;
}
