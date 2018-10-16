package com.platform.service;

import com.platform.entity.RelatedGoodsEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-13 10:41:09
 */
public interface RelatedGoodsService {
	
	RelatedGoodsEntity queryObject(Integer id);
	
	List<RelatedGoodsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(RelatedGoodsEntity relatedGoods);
	
	void update(RelatedGoodsEntity relatedGoods);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
