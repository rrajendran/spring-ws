package com.capella.users;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capella.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/com/capella/spring/ws/users/applicationContext.xml"})
public class UserServiceTest {
	
	private static final String PASSWD = "passwd";
	private static final String USER_NAME = "testuser";
	@Autowired
	private UserService userService;
	
	@Before
	public void testSave() {
		User user = new User(USER_NAME,PASSWD);
		userService.save(user);
	}

	@Test
	public void testGetUser() {
		User user = userService.getUser(USER_NAME);
		assertNotNull(user);
		assertEquals(USER_NAME,user.getUsername());
	}
	
	@org.junit.After
	public void tearDown(){
		userService.delete(USER_NAME);
	}

}
