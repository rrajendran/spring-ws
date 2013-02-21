package com.capella.endpoints;

import o2.co.uk.validators.EmailValidation;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.capella.exceptions.EmailValidationException;
import com.spring_ws.person.schemas.ObjectFactory;
import com.spring_ws.person.schemas.PersonRequest;
import com.spring_ws.person.schemas.PersonResponse;

@Endpoint
public class PersonEndpoint{
	private static Logger LOGGER = Logger.getLogger(PersonEndpoint.class);

	private static final String NAMESPACE = "http://spring-ws.com/person/schemas";
	public PersonEndpoint() {
		LOGGER.info("Constructor invoked");
	}
	
	/**
	 * Gets person list.
	 * @throws EmailValidationException 
	 */
	@PayloadRoot(localPart="PersonRequest", namespace=NAMESPACE)
	public @ResponsePayload PersonResponse getPersonRequest(@RequestPayload PersonRequest request, MessageContext messageContext) throws EmailValidationException {
		if(request != null && StringUtils.isBlank(request.getName())){
			throw new NullPointerException("Email id is empty");
		}
		
		if(!EmailValidation.validateEmail(request.getName()))
			throw new EmailValidationException("Invalid email id");
		PersonResponse createPersonResponse = new ObjectFactory().createPersonResponse();
		createPersonResponse.setEmailid(request.getName());
		return createPersonResponse;
	}
}
