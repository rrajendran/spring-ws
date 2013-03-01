package com.capella.endpoints;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.capella.entity.User;
import com.capella.exceptions.SpringWsException;
import com.capella.users.UserService;
import com.spring_ws.person.schemas.ObjectFactory;
import com.spring_ws.person.schemas.PersonRequest;
import com.spring_ws.person.schemas.PersonResponse;
/**
 * Person service endpoint 
 * @author rrajendran
 */
@Endpoint
public class PersonEndpoint{
	private static Logger LOGGER = Logger.getLogger(PersonEndpoint.class);
	@Autowired
	private UserService userService;
	private static final String NAMESPACE = "http://spring-ws.com/person/schemas";
	
	/**
	 * Constructor
	 */
	public PersonEndpoint() {
		LOGGER.info("Endpoint constructor invoked");
	}
	
	/**
	 * Gets username and password for a given username
	 * @throws SpringWsException 
	 */
	@PayloadRoot(localPart="PersonRequest", namespace=NAMESPACE)
	public @ResponsePayload PersonResponse getPersonRequest(@RequestPayload PersonRequest request, 
			MessageContext messageContext) throws SpringWsException{
		PersonResponse createPersonResponse = new ObjectFactory().createPersonResponse();
		String username = request.getUsername();
		if(username != null){
			User user = userService.getUser(username);
			createPersonResponse.setUsername(user.getUsername());
			createPersonResponse.setPassword(user.getPassword());
		}
		
		return createPersonResponse;
	}
}
