package com.platform.entity.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Example基类
 *
 * @author xuyang
 * @email 295640759@qq.com
 * @date 2018年08月08日 上午10:43:36
 */
public abstract class AbstractExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<GeneratedCriteria> oredCriteria;

	protected int pageStart = -1;

	protected int pageSize = -1;

	public AbstractExample() {
		oredCriteria = new ArrayList<GeneratedCriteria>();
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public List<GeneratedCriteria> getOredCriteria() {
		return oredCriteria;
	}

	public void or(GeneratedCriteria criteria) {
		oredCriteria.add(criteria);
	}

	public GeneratedCriteria or() {
		GeneratedCriteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	public GeneratedCriteria createCriteria() {
		GeneratedCriteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}

	public int getPageStart() {
		return pageStart;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 子类需实现该方法
	 * 
	 * @return
	 */
	protected abstract GeneratedCriteria createCriteriaInternal();

}
