package com.huoyun.common.persistence;

import java.io.Serializable;

import org.joda.time.DateTime;

public interface Entity extends Serializable{

	Long getId();

	void setCreateTime(DateTime createTime);

	void setUpdateTime(DateTime updateTime);

	Long getVersion();

	void beforeCreate();

	void afterCreate();

	void beforeUpdate();

	void afterUpdate();

	void beforeDelete();

	void afterDelete();

}
