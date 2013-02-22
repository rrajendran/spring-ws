package com.capella.endpoints;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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
public class PersonServiceTest {
	@Autowired
	private WebServiceTemplate webServiceTemplate;

	@Test
	public void testWebserviwebServiceTemplate(){
		try {
			PersonRequest person = new ObjectFactory().createPersonRequest();
			person.setFirstName("Hello");
			person.setLastName("World");
			
			PersonResponse personResponse = (PersonResponse) webServiceTemplate.marshalSendAndReceive(person);
			Assert.assertEquals("Hello World", personResponse.getFullName());
		} catch (Exception e) {
			Assert.fail("Problem executing the webservice : " + e.getMessage());
		}
	}
}

