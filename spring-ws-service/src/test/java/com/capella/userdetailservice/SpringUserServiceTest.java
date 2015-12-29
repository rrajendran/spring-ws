package com.capella.userdetailservice;

import com.capella.users.model.User;
import com.capella.users.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:com/capella/spring/ws/service/applicationContext.xml"})
@Transactional
public class SpringUserServiceTest {
	@Autowired
	private SpringUserService springUserService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Before
	public void setUp(){
		User user = new User("test","test123");
		userRepository.save(user);
		
	}
	@Test
	public void testLoadUserByUsername() {
		UserDetails userDetails = springUserService.loadUserByUsername("test");
		assertNotNull(userDetails);
		assertEquals("test",userDetails.getUsername());
	}

}
