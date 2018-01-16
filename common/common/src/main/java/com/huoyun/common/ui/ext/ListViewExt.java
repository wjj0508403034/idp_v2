package com.huoyun.common.ui.ext;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.huoyun.common.persistence.BaseEntity;

@Entity
@Table
public class ListViewExt extends BaseEntity {

	private static final long serialVersionUID = 8680274180866898780L;

	@Column
	private String boName;

	@Column
	private String boNamespace;

	@Column
	private String columns;

	@Column
	private Long ownerId;

	public String getBoName() {
		return boName;
	}

	public void setBoName(String boName) {
		this.boName = boName;
	}

	public String getBoNamespace() {
		return boNamespace;
	}

	public void setBoNamespace(String boNamespace) {
		this.boNamespace = boNamespace;
	}

	public String getColumns() {
		return columns;
	}

	public void setColumns(String columns) {
		this.columns = columns;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

}
