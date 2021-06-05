package com.Threado.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Threado.model.CustomUserDetails;
import com.Threado.model.Customer;
import com.Threado.repository.CustomerRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	Logger log = LoggerFactory.getLogger(MyUserDetailsService.class);

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Customer user = customerRepository.findByEmail(userName);
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(userName));
		if (user != null) {
			return getUserForAuth(user, grantedAuthorities);
		} else {
			return null;
		}
	}

	private UserDetails getUserForAuth(Customer userModel, Collection<GrantedAuthority> grantedAuthorities) {
		CustomUserDetails customUserDeatils = new CustomUserDetails(userModel);
		return customUserDeatils;
	}
}
