package com.capella.endpoints;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.capella.entity.User;
import com.capella.users.UserService;
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
public class PersonServiceTest {
	@Autowired
	private WebServiceTemplate webServiceTemplate;
	
	@Autowired
	private UserService userService;
	@Before
	public void setUp(){
		User user = new User("mojo", "mojopass");
		userService.save(user);
	}
	
	@Test
	public void testWebserviwebServiceTemplate(){
		PersonRequest person = new ObjectFactory().createPersonRequest();
		person.setFirstName("Hello");
		person.setLastName("World");
		
		PersonResponse personResponse = (PersonResponse) webServiceTemplate.marshalSendAndReceive(person);
		Assert.assertEquals("Hello World", personResponse.getFullName());
	}
}

