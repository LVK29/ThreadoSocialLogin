package com.Threado.security.social;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.Threado.model.Customer;
import com.Threado.repository.CustomerRepository;
import com.Threado.security.service.MyUserDetailsService;

import jdk.internal.org.jline.utils.Log;

@Component
public class CustomerAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication paramAuthentication) throws AuthenticationException {
		// TODO Auto-generated method stub

		final CustomerAuthenticationToken customerSocialToken = (CustomerAuthenticationToken) paramAuthentication;

		// check if fields are valid/ not null etc

		String customerEmail = customerSocialToken.getEmail();
		UserDetails userDetails = getCustomerUserDetails(customerEmail);
		if (userDetails == null) {
			// create new userdetials for user
			createNewCustomer(customerSocialToken);
		} else {
			// update existing Customer model
			updateCustomer(customerSocialToken, customerEmail);
		}

		userDetails = getCustomerUserDetails(customerSocialToken.getEmail());

		return createSuccessAuthentication(paramAuthentication.getPrincipal(), paramAuthentication, userDetails);
	}

	private void updateCustomer(final CustomerAuthenticationToken customerSocialToken, String customerEmail) {
		Customer customerModel = customerRepository.findByEmail(customerEmail);
		customerModel.setName(customerSocialToken.getName());
		customerModel.setLoginSource(customerSocialToken.getLoginSource());
		customerModel.setProfileImage(customerSocialToken.getProfilePic());
		customerRepository.save(customerModel);
	}

	private void createNewCustomer(final CustomerAuthenticationToken customerSocialToken) {
		Customer customerModel = new Customer();
		customerModel.setEmail(customerSocialToken.getEmail());
		customerModel.setName(customerSocialToken.getName());
		customerModel.setLoginSource(customerSocialToken.getLoginSource());
		customerModel.setProfileImage(customerSocialToken.getProfilePic());
		customerRepository.save(customerModel);
	}

	private Authentication createSuccessAuthentication(Object principal, Authentication authentication,
			UserDetails userDetails) {

		CustomerAuthenticationToken result = new CustomerAuthenticationToken(principal.toString().toLowerCase(),
				userDetails.getAuthorities());
		result.setDetails(authentication.getDetails());
		return result;
	}

	private UserDetails getCustomerUserDetails(String email) {
		try {
			UserDetails userDetails = null;
			userDetails = this.getUserDetailsService().loadUserByUsername(email);
			return userDetails;
		} catch (final UsernameNotFoundException une) {
			Log.info("EXCEPTION user name not found", une);
			return null;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {

		return Objects.nonNull(authentication) && CustomerAuthenticationToken.class.isAssignableFrom(authentication);
	}

	protected UserDetailsService getUserDetailsService() {
		return this.userDetailsService;
	}
}
