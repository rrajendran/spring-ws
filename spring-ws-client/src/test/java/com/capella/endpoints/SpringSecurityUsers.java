package com.capella.endpoints;

import com.capella.users.model.User;
import com.capella.users.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.security.wss4j.Wss4jSecurityInterceptor;

@Service
public class SpringSecurityUsers {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SpringSecurityUsers.class);
	@Autowired
	private UserRepository userService;
	
	private User user = null;

	public User getUser(){
		userService.deleteAll();
		user = new User("testuser", "passwd");
		userService.save(user);
		LOGGER.debug("Create users = " + user);
		return user;
	}

	public ClientInterceptor[] getWss4jSecurityInterceptor(WebServiceTemplate webServiceTemplate){
		LOGGER.info("Create Wss4jSecurityInterceptor");
		ClientInterceptor[] ci = webServiceTemplate.getInterceptors();
		Wss4jSecurityInterceptor wsSec  = (Wss4jSecurityInterceptor)ci[0];
		wsSec.setSecurementUsername(getUser().getUsername());
		wsSec.setSecurementPassword(getUser().getPassword());
		webServiceTemplate.setInterceptors(ci);
		return ci;
	}
}
