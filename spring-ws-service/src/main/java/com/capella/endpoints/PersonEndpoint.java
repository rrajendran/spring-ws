package com.capella.endpoints;


import com.capella.exceptions.SpringWsException;
import com.capella.users.model.User;
import com.capella.users.repository.UserRepository;
import com.spring_ws.person.schemas.ObjectFactory;
import com.spring_ws.person.schemas.PersonRequest;
import com.spring_ws.person.schemas.PersonResponse;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Person service endpoint 
 * @author rrajendran
 */
@Endpoint
public class PersonEndpoint{
	public static final Logger LOGGER = getLogger(PersonEndpoint.class);

	@Autowired
	private UserRepository userService;
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
			User user = userService.findByUsername(username);
			createPersonResponse.setUsername(user.getUsername());
			createPersonResponse.setPassword(user.getPassword());
		}
		
		return createPersonResponse;
	}
}
