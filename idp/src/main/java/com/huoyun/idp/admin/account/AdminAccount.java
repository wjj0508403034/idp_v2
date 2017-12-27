package com.huoyun.idp.admin.account;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huoyun.common.metadata.annotation.BusinessObject;
import com.huoyun.common.metadata.annotation.BusinessObjectProperty;
import com.huoyun.common.persistence.BaseEntity;

@BusinessObject
@Entity
@Table
public class AdminAccount extends BaseEntity {

	private static final long serialVersionUID = -5766309354484780664L;

	@Column
	@BusinessObjectProperty
	private String firstName;

	@Column
	@BusinessObjectProperty
	private String lastName;

	@Column
	@BusinessObjectProperty
	private String email;

	@Column
	@BusinessObjectProperty(exposed = false, readonly = true)
	private String password;

	@Column
	@BusinessObjectProperty
	private boolean locked;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonIgnore
	public Collection<? extends GrantedAuthority> getGrantedAuthorityList() {
		return AuthorityUtils.createAuthorityList("ROLE_ADMIN");
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

}
