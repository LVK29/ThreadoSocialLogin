package com.Threado.security.social;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;


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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getLoginSource() {
		return loginSource;
	}

	public void setLoginSource(String loginSource) {
		this.loginSource = loginSource;
	}
		

}
