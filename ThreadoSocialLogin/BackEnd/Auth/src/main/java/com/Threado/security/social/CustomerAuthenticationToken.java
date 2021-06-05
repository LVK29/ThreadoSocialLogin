package com.Threado.security.social;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerAuthenticationToken extends AbstractAuthenticationToken {

	private String email;
	private String name;
	private String profilePic;

	private String loginSource;

	public CustomerAuthenticationToken(String email, String name, String profilePic, String loginSource) {
		super(null);
		this.email = email;
		this.name = name;
		this.profilePic = profilePic;
		this.loginSource=loginSource;
	}

	public CustomerAuthenticationToken(final String email, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.email = email;
		setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return email;
	}

}
