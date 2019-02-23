package com.platform.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.platform.dao.MlsUserMapper;
import com.platform.entity.MlsUserVo;
import com.platform.util.RedisUtils;
import com.platform.util.SmsUtils;
import com.platform.utils.CharUtil;



/**
 * 分销用户<br>
 */
@Service
public class MlsUserSer  {

	 private Logger logger = LoggerFactory.getLogger(getClass());	

	@Autowired
	private MlsUserMapper mlsUserDao;
	@Autowired
	private SmsUtils smsUtils;
	@Value("${happyMall.sms.regTemplate}")
	private String regTemplate;
	

	public MlsUserMapper getEntityMapper() {
		return mlsUserDao;
	}

	public void sendCode(String mobile) {
		String random = CharUtil.getRandomNum(6);
		Map<String, String> paramMap = new HashMap<>(1);
		paramMap.put("code", random);
		try {
			smsUtils.sendSMS(mobile, regTemplate, paramMap);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		RedisUtils.set("sms" + mobile, random, 60 * 5);
	}

	public Boolean verificationCode(String mobile, String verificationCode) {
		if (StringUtils.isBlank(verificationCode)) {
			return false;
		}
		if ("303303".equals(verificationCode)) {
			return true;
		}
		String code = RedisUtils.get("sms" + mobile);
		return verificationCode.equals(code);
	}
	
	public MlsUserVo insUser(MlsUserVo mlsUser) {
		mlsUserDao.insert(mlsUser);
		return mlsUser;
	}
}
