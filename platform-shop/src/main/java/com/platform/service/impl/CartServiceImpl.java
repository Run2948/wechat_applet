package com.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.platform.dao.CartDao;
import com.platform.entity.CartEntity;
import com.platform.service.CartService;


@Service("cartService")
public class CartServiceImpl implements CartService {
	@Autowired
	private CartDao cartDao;
	
	@Override
	public CartEntity queryObject(Integer id){
		return cartDao.queryObject(id);
	}
	
	@Override
	public List<CartEntity> queryList(Map<String, Object> map){
		return cartDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cartDao.queryTotal(map);
	}
	
	@Override
	public void save(CartEntity cart){
		cartDao.save(cart);
	}
	
	@Override
	public void update(CartEntity cart){
		cartDao.update(cart);
	}
	
	@Override
	public void delete(Integer id){
		cartDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		cartDao.deleteBatch(ids);
	}
	
}
