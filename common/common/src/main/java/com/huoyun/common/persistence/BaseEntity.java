package com.huoyun.common.persistence;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huoyun.common.metadata.annotation.BusinessObjectProperty;

@MappedSuperclass
public abstract class BaseEntity implements Entity {

	private static final long serialVersionUID = 2536399227490960286L;

	@Id
	@GenericGenerator(name = "IdGenerator", strategy = "com.huoyun.common.persistence.IdGenerator")
	@GeneratedValue(generator = "IdGenerator")
	@BusinessObjectProperty(readonly = true)
	private Long id;

	@Version
	private Long version;

	@Column
	@BusinessObjectProperty(readonly = true)
	private DateTime createTime;

	@Column
	@BusinessObjectProperty(readonly = true)
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

	@Override
	public final void beforeCreate() {
		DateTime now = DateTime.now();
		this.setCreateTime(now);
		this.setUpdateTime(now);
		this.preCreate();
	}

	protected void preCreate() {

	}

	@Override
	public final void afterCreate() {
		this.postCreate();
	}

	protected void postCreate() {

	}

	@Override
	public final void beforeUpdate() {
		this.setUpdateTime(DateTime.now());
		this.preUpdate();
	}

	protected void preUpdate() {

	}

	@Override
	public final void afterUpdate() {
		this.postUpdate();
	}

	protected void postUpdate() {

	}
	
	@Override
	public final void beforeDelete(){
		this.preDelete();
	}
	
	protected void preDelete(){
		
	}
	
	@Override
	public final void afterDelete(){
		this.postDelete();
	}
	
	protected void postDelete(){
		
	}
}
