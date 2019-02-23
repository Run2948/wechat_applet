package com.platform.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.platform.cache.J2CacheUtils;
import com.platform.dao.ApiTokenMapper;
import com.platform.entity.TokenEntity;
import com.platform.util.ApiUserUtils;
import com.platform.util.RedisUtils;
import com.platform.utils.CharUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class TokenService {
    @Autowired
    private ApiTokenMapper tokenDao;
    @Autowired
    private RestTemplate restTemplate;
    
    //12小时后过期
    private final static int EXPIRE = 3600 * 12;
    //2小时后过期
    private final static int EXPIRE2 = 3600 * 2;

    public TokenEntity queryByUserId(Long userId) {
        return tokenDao.queryByUserId(userId);
    }

    public TokenEntity queryByToken(String token) {
        return tokenDao.queryByToken(token);
    }

    public void save(TokenEntity token) {
        tokenDao.save(token);
    }

    public void update(TokenEntity token) {
        tokenDao.update(token);
    }

    public Map<String, Object> createToken(long userId) {
        //生成一个token
        String token = CharUtil.getRandomString(32);
        //当前时间
        Date now = new Date();

        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        TokenEntity tokenEntity = queryByUserId(userId);
        if (tokenEntity == null) {
            tokenEntity = new TokenEntity();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //保存token
            save(tokenEntity);
        } else {
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //更新token
            update(tokenEntity);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("expire", EXPIRE);

        return map;
    }
    
    public String getAccessToken() {
    	String access_token = null;
    	
    	boolean flag = RedisUtils.exists(J2CacheUtils.SYSTEM_ACCESS_TOKEN);
    	if(flag == true) {
    		access_token = RedisUtils.get(J2CacheUtils.SYSTEM_ACCESS_TOKEN).toString();
        	if(StringUtils.isNotBlank(access_token)) {
        		return access_token;
        	}
    	}
    	
    	
    	//获取access_token
        String requestUrl = ApiUserUtils.getAccessToken();
        String res = restTemplate.getForObject(requestUrl, String.class);
        JSONObject sessionData = JSON.parseObject(res);
        access_token=sessionData.getString("access_token");
        
        RedisUtils.set(J2CacheUtils.SYSTEM_ACCESS_TOKEN, access_token, EXPIRE2);
        return access_token;
        
    }
}
