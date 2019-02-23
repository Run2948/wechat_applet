package com.platform.util;

import java.io.Serializable;
import java.util.List;
/**
 * 基础dao
 * @author wengl
 */
public interface EntityDao <E,PK extends Serializable> {
	
	public E getById(PK id) ;
	
	public void deleteById(PK id) ;
	
	/** 插入数据 */
	public void insert(E entity) ;
	
	/** 更新数据 */
	public int update(E entity) ;
  	/**
     * 根据条件删除数据
     */
  	public void removeByEntity(E entity) ;
	
	/** 按条件查询方法 */
	public List<E> findByEntity(E entity);
	/** 按条件 数数量*/
	public long findByEntity_count(E entity);
	
}
