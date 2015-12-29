package com.capella.endpoints;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.spring_ws.person.schemas.ObjectFactory;
import com.spring_ws.person.schemas.PersonRequest;
import com.spring_ws.person.schemas.PersonResponse;
/**
 * 
 * @author rrajendran
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:testApplicationContext.xml"})
@Transactional
public class PersonServiceTest {
	@Autowired
	private WebServiceTemplate webServiceTemplate;
	
	@Autowired
	private SpringSecurityUsers springSecurityUsers;
	
	@Before
	public void setUp(){
         webServiceTemplate.setInterceptors(springSecurityUsers.getWss4jSecurityInterceptor(webServiceTemplate));
	}
	
	@Test
	public void testWebserviwebServiceTemplate(){
		PersonResponse personResponse = (PersonResponse) webServiceTemplate.marshalSendAndReceive(getPersonRequest());
		Assert.assertEquals("john", personResponse.getUsername());
		Assert.assertEquals("pass", personResponse.getPassword());
	}
	
	private PersonRequest getPersonRequest() {
		PersonRequest person = new ObjectFactory().createPersonRequest();
		person.setUsername("john");
		return person;
	}
}

