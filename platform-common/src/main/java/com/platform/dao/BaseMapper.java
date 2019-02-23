package com.platform.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Mapper基类：只包含Mybatis自动生成的基本方法
 *
 * @param <T> example类
 * @param <E> entity类
 *
 * @author xuyang
 * @email 295640759@qq.com
 * @date 2018年8月9日 上午9:31:36
 */
public interface BaseMapper<T, E> {

	/**
	 * 根据example条件查询满足条件记录总数
	 * @param example
	 * @return
	 */
	int countByExample(E example);

	/**
	 * 按example条件物理删除记录<br>
	 * 慎用：如果example不小心传空，则可能把整个表删除
	 * @param example
	 * @return
	 */
	@Deprecated
	int deleteByExample(E example);

	/**
	 * 按主键物理删除记录<br>
	 * 慎用：建议逻辑删除
	 * @param id
	 * @return
	 */
	@Deprecated
	int deleteByPrimaryKey(Long id);

	/**
	 * 按主键批量物理删除记录<br>
	 * 慎用：建议逻辑删除
	 * @param id
	 * @return
	 */
	@Deprecated
	int deleteBatch(Object[] id);

	/**
	 * 插入record中所有字段，包括null字段<br>
	 * 如果数据库字段非空，则慎用该方法
	 * @param record
	 * @return
	 */
	@Deprecated
	int insert(T record);

	/**
	 * 按record记录非空字段写入
	 * @param record
	 * @return
	 */
	int insertSelective(T record);

	/**
	 * 根据example条件查询记录，返回列表
	 * @param example
	 * @return
	 */
	List<T> selectByExample(E example);

	/**
	 * 根据主键查询记录
	 * @param id
	 * @return
	 */
	T selectByPrimaryKey(Long id);

	/**
	 * 根据example条件将record中非空字段更新到数据库
	 * @param record
	 * @param example
	 * @return
	 */
	int updateByExampleSelective(@Param("record") T record, @Param("example") E example);

	/**
	 * 根据example条件将record中所有字段更新到数据库<br>
	 * 慎用：有可能会把不该清空的字段清空
	 * @param record
	 * @param example
	 * @return
	 */
	int updateByExample(@Param("record") T record, @Param("example") E example);

	/**
	 * 根据主键将record中非空字段更新到数据库
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(T record);

	/**
	 * 根据主键更新所有字段 慎用：有可能会把不该清空的字段清空
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(T record);

	/**
	 * 兼容老版本
	 *
	 * 根据条件查询列表
	 *
	 * @param map
	 * @return
	 */
	List<T> queryList(Map<String, Object> map);

	/**
	 * 兼容老版本
	 *
	 * 根据条件查询满足条件记录总数
	 *
	 * @param map
	 * @return
	 */
	int queryTotal(Map<String, Object> map);
}
