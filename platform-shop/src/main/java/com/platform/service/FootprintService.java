package com.platform.service;

import com.platform.entity.FootprintEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-13 10:41:08
 */
public interface FootprintService {
	
	FootprintEntity queryObject(Integer id);
	
	List<FootprintEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(FootprintEntity footprint);
	
	void update(FootprintEntity footprint);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
