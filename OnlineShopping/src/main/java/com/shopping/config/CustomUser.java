package com.shopping.config;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.shopping.model.UserDtls;

public class CustomUser implements UserDetails {

	private UserDtls user;

	public CustomUser(UserDtls user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//The getAuthorities() method is responsible for returning the authorities granted to the user. 
		//Authorities represent roles or permissions that a user has and are used for authorization purposes.
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
		return Arrays.asList(authority);
		//This method provides the authorities (roles or permissions) assigned to the user. 
		//These authorities are used by Spring Security to determine whether the user has access to 
		//specific resources or actions within the application.
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
