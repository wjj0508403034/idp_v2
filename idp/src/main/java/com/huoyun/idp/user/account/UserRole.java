package com.huoyun.idp.user.account;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.huoyun.common.persistence.BaseEntity;

@Entity
@Table
public class UserRole extends BaseEntity {

	private static final long serialVersionUID = -6784128575656020226L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
