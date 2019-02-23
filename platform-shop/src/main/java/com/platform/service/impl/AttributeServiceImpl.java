package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.AttributeDao;
import com.platform.entity.AttributeEntity;
import com.platform.service.AttributeService;


@Service("attributeService")
public class AttributeServiceImpl implements AttributeService {
	@Autowired
	private AttributeDao attributeDao;
	
	@Override
	public AttributeEntity queryObject(Integer id){
		return attributeDao.queryObject(id);
	}
	
	@Override
	public List<AttributeEntity> queryList(Map<String, Object> map){
		return attributeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return attributeDao.queryTotal(map);
	}
	
	@Override
	public void save(AttributeEntity attribute){
		attributeDao.save(attribute);
	}
	
	@Override
	public void update(AttributeEntity attribute){
		attributeDao.update(attribute);
	}
	
	@Override
	public void delete(Integer id){
		attributeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		attributeDao.deleteBatch(ids);
	}
	
}
