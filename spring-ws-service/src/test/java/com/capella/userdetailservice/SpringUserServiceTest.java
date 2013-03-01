package com.capella.userdetailservice;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capella.entity.User;
import com.capella.users.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:/com/capella/spring/ws/service/applicationContext.xml"})
public class SpringUserServiceTest {
	@Autowired
	private SpringUserService springUserService;
	
	@Autowired
	private UserService userService;
	
	@Before
	public void setUp(){
		User user = new User("test","test123");
		userService.save(user);
		
	}
	@Test
	public void testLoadUserByUsername() {
		UserDetails userDetails = springUserService.loadUserByUsername("test");
		assertNotNull(userDetails);
		assertEquals("test",userDetails.getUsername());
	}

}
