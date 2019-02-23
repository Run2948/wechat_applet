package com.platform.dao;

import java.util.List;
import java.util.Map;

import com.platform.entity.CustomerEntity;

/**
 * 客户Dao
 */
public interface CustomerDao extends BaseDao<CustomerEntity> {
	  List<CustomerEntity> query2List(Map<String, Object> map);
	 int query2Total(Map<String, Object> map);
}
