package com.huoyun.idp.admin.authentication;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class AdminAuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = 7603468723481735796L;

	private Collection<GrantedAuthority> authorities = new ArrayList<>();
	private Object principal;
	private String username;
	private String password;

	public AdminAuthenticationToken(String username, String password) {
		super(null);

		this.username = username;
		this.password = password;
	}

	@Override
	public Object getCredentials() {
		return this.password;
	}

	@Override
	public Object getPrincipal() {
		return this.principal;
	}

	public String getUsername() {
		return this.username;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public void setDetails(Object details) {
		super.setDetails(details);
		this.principal = details;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities.addAll(authorities);
	}

}
