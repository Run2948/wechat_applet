package com.platform.entity.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 封装and条件
 *
 * @author xuyang
 * @email 295640759@qq.com
 * @date 2018年08月08日 上午10:43:36
 */
public abstract class GeneratedCriteria {
	protected List<Criterion> criteria;

	protected GeneratedCriteria() {
		criteria = new ArrayList<Criterion>();
	}

	public boolean isValid() {
		return criteria.size() > 0;
	}

	public List<Criterion> getAllCriteria() {
		return criteria;
	}

	public List<Criterion> getCriteria() {
		return criteria;
	}

	protected void addCriterion(String condition) {
		if (condition == null) {
			throw new RuntimeException("Value for condition cannot be null");
		}
		criteria.add(new Criterion(condition));
	}

	protected void addCriterion(String condition, Object value, String property) {
		if (value == null) {
			throw new RuntimeException("Value for " + property + " cannot be null");
		}
		criteria.add(new Criterion(condition, value));
	}

	protected void addCriterion(String condition, Object value1, Object value2, String property) {
		if (value1 == null || value2 == null) {
			throw new RuntimeException("Between values for " + property + " cannot be null");
		}
		criteria.add(new Criterion(condition, value1, value2));
	}

	protected void addCriterionForJDBCDate(String condition, Date value, String property) {
		if (value == null) {
			throw new RuntimeException("Value for " + property + " cannot be null");
		}
		addCriterion(condition, new java.sql.Date(value.getTime()), property);
	}

	protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
		if (values == null || values.size() == 0) {
			throw new RuntimeException("Value list for " + property + " cannot be null or empty");
		}
		List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
		Iterator<Date> iter = values.iterator();
		while (iter.hasNext()) {
			dateList.add(new java.sql.Date(iter.next().getTime()));
		}
		addCriterion(condition, dateList, property);
	}

	protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
		if (value1 == null || value2 == null) {
			throw new RuntimeException("Between values for " + property + " cannot be null");
		}
		addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
	}

	public GeneratedCriteria andIdIsNull() {
		addCriterion("id is null");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andIdIsNotNull() {
		addCriterion("id is not null");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andIdEqualTo(Long value) {
		addCriterion("id =", value, "id");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andIdNotEqualTo(Long value) {
		addCriterion("id <>", value, "id");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andIdGreaterThan(Long value) {
		addCriterion("id >", value, "id");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andIdGreaterThanOrEqualTo(Long value) {
		addCriterion("id >=", value, "id");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andIdLessThan(Long value) {
		addCriterion("id <", value, "id");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andIdLessThanOrEqualTo(Long value) {
		addCriterion("id <=", value, "id");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andIdIn(List<Long> values) {
		addCriterion("id in", values, "id");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andIdNotIn(List<Long> values) {
		addCriterion("id not in", values, "id");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andIdBetween(Long value1, Long value2) {
		addCriterion("id between", value1, value2, "id");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andIdNotBetween(Long value1, Long value2) {
		addCriterion("id not between", value1, value2, "id");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateIdIsNull() {
		addCriterion("create_id is null");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateIdIsNotNull() {
		addCriterion("create_id is not null");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateIdEqualTo(Date value) {
		addCriterion("create_id =", value, "createId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateIdNotEqualTo(Date value) {
		addCriterion("create_id <>", value, "createId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateIdGreaterThan(Date value) {
		addCriterion("create_id >", value, "createId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateIdGreaterThanOrEqualTo(Date value) {
		addCriterion("create_id >=", value, "createId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateIdLessThan(Date value) {
		addCriterion("create_id <", value, "createId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateIdLessThanOrEqualTo(Date value) {
		addCriterion("create_id <=", value, "createId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateIdIn(List<Date> values) {
		addCriterion("create_id in", values, "createId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateIdNotIn(List<Date> values) {
		addCriterion("create_id not in", values, "createId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateIdBetween(Date value1, Date value2) {
		addCriterion("create_id between", value1, value2, "createId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateIdNotBetween(Date value1, Date value2) {
		addCriterion("create_id not between", value1, value2, "createId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpdateIdIsNull() {
		addCriterion("update_id is null");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpdateIdIsNotNull() {
		addCriterion("update_id is not null");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpdateIdEqualTo(Long value) {
		addCriterion("update_id =", value, "updateId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpdateIdNotEqualTo(Long value) {
		addCriterion("update_id <>", value, "updateId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpdateIdGreaterThan(Long value) {
		addCriterion("update_id >", value, "updateId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpdateIdGreaterThanOrEqualTo(Long value) {
		addCriterion("update_id >=", value, "updateId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpdateIdLessThan(Long value) {
		addCriterion("update_id <", value, "updateId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpdateIdLessThanOrEqualTo(Long value) {
		addCriterion("update_id <=", value, "updateId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpdateIdIn(List<Long> values) {
		addCriterion("update_id in", values, "updateId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpdateIdNotIn(List<Long> values) {
		addCriterion("update_id not in", values, "updateId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpdateIdBetween(Long value1, Long value2) {
		addCriterion("update_id between", value1, value2, "updateId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpdateIdNotBetween(Long value1, Long value2) {
		addCriterion("update_id not between", value1, value2, "updateId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateTimeIsNull() {
		addCriterion("create_time is null");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateTimeIsNotNull() {
		addCriterion("create_time is not null");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateTimeEqualTo(Date value) {
		addCriterion("create_time =", value, "createTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateTimeNotEqualTo(Date value) {
		addCriterion("create_time <>", value, "createTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateTimeGreaterThan(Date value) {
		addCriterion("create_time >", value, "createTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateTimeGreaterThanOrEqualTo(Date value) {
		addCriterion("create_time >=", value, "createTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateTimeLessThan(Date value) {
		addCriterion("create_time <", value, "createTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateTimeLessThanOrEqualTo(Date value) {
		addCriterion("create_time <=", value, "createTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateTimeIn(List<Date> values) {
		addCriterion("create_time in", values, "createTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateTimeNotIn(List<Date> values) {
		addCriterion("create_time not in", values, "createTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateTimeBetween(Date value1, Date value2) {
		addCriterion("create_time between", value1, value2, "createTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateTimeNotBetween(Date value1, Date value2) {
		addCriterion("create_time not between", value1, value2, "createTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpdateTimeIsNull() {
		addCriterion("update_time is null");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpdateTimeIsNotNull() {
		addCriterion("update_time is not null");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpdateTimeEqualTo(Date value) {
		addCriterion("update_time =", value, "updateTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpdateTimeNotEqualTo(Date value) {
		addCriterion("update_time <>", value, "updateTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpdateTimeGreaterThan(Date value) {
		addCriterion("update_time >", value, "updateTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
		addCriterion("update_time >=", value, "updateTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpdateTimeLessThan(Date value) {
		addCriterion("update_time <", value, "updateTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpdateTimeLessThanOrEqualTo(Date value) {
		addCriterion("update_time <=", value, "updateTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpdateTimeIn(List<Date> values) {
		addCriterion("update_time in", values, "updateTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpdateTimeNotIn(List<Date> values) {
		addCriterion("update_time not in", values, "updateTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpdateTimeBetween(Date value1, Date value2) {
		addCriterion("update_time between", value1, value2, "updateTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpdateTimeNotBetween(Date value1, Date value2) {
		addCriterion("update_time not between", value1, value2, "updateTime");
		return (GeneratedCriteria) this;
	}
}
