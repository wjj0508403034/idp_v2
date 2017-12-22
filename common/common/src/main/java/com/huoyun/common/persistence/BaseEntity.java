package com.huoyun.common.persistence;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class BaseEntity implements Entity {

	private static final long serialVersionUID = 2536399227490960286L;

	@Id
	@GenericGenerator(name = "IdGenerator", strategy = "com.huoyun.common.persistence.IdGenerator")
	@GeneratedValue(generator = "IdGenerator")
	private Long id;

	@Version
	private Long version;

	@Column
	private DateTime createTime;

	@Column
	private DateTime updateTime;

	@JsonIgnore
	@Override
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public DateTime getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(DateTime createTime) {
		this.createTime = createTime;
	}

	public DateTime getUpdateTime() {
		return updateTime;
	}

	@Override
	public void setUpdateTime(DateTime updateTime) {
		this.updateTime = updateTime;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
		return this.id;
	}
}
