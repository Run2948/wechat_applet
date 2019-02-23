package com.platform.entity.example;

import java.util.List;

/**
 * 封装SQL查询条件和值
 *
 * @author xuyang
 * @email 295640759@qq.com
 * @date 2018年08月08日 上午10:43:36
 */
public class Criterion {
	private String condition;
	private Object value;
	private Object secondValue;
	private boolean noValue;
	private boolean singleValue;
	private boolean betweenValue;
	private boolean listValue;
	private String typeHandler;

	public String getCondition() {
		return condition;
	}

	public Object getValue() {
		return value;
	}

	public Object getSecondValue() {
		return secondValue;
	}

	public boolean isNoValue() {
		return noValue;
	}

	public boolean isSingleValue() {
		return singleValue;
	}

	public boolean isBetweenValue() {
		return betweenValue;
	}

	public boolean isListValue() {
		return listValue;
	}

	public String getTypeHandler() {
		return typeHandler;
	}

	protected Criterion(String condition) {
		super();
		this.condition = condition;
		this.typeHandler = null;
		this.noValue = true;
	}

	protected Criterion(String condition, Object value, String typeHandler) {
		super();
		this.condition = condition;
		this.value = value;
		this.typeHandler = typeHandler;
		if (value instanceof List<?>) {
			this.listValue = true;
		} else {
			this.singleValue = true;
		}
	}

	protected Criterion(String condition, Object value) {
		this(condition, value, null);
	}

	protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
		super();
		this.condition = condition;
		this.value = value;
		this.secondValue = secondValue;
		this.typeHandler = typeHandler;
		this.betweenValue = true;
	}

	protected Criterion(String condition, Object value, Object secondValue) {
		this(condition, value, secondValue, null);
	}
}
