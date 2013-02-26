package com.capella.spring.ws.users.test;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capella.users.User;
import com.capella.users.UserRepository;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class UserRepositoryTest {
	@Autowired
	UserRepository userRepository;
	
	@Test
	public void testInsertUser(){
		User user = new User();
		User save = userRepository.save(user);
		Assert.assertNotNull(save.getId());
	}
}
