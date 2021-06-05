package com.Threado.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {

		
		http.authorizeRequests();//.antMatchers("/register").permitAll();
		http.csrf().disable().authorizeRequests().anyRequest().authenticated();


	}

//	private static final String RESOURCE_ID = "digitalEntryPass";
//
//	@Override
//	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//		resources.resourceId(RESOURCE_ID);
//	}

}