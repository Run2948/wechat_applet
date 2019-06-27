package com.platform.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.dao.UserRecordMapper;
import com.platform.entity.OrderVo;
import com.platform.entity.UserRecord;

/**
 * <br>
 */
@Service
public class UserRecordSer  {
	
	 private Logger logger = LoggerFactory.getLogger(getClass());	
	
	@Autowired
	private UserRecordMapper userRecordDao;
	
	public UserRecordMapper getEntityMapper(){    	
    	return userRecordDao;
    }

    public void save(UserRecord ur) {
    	userRecordDao.insert(ur);
    }
	
}
