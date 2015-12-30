package com.capella.users.repository;

import com.capella.users.model.User;
import com.capella.users.model.UserBuilder;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@Transactional
@Ignore
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldFindUser() {
        // given
        User user = UserBuilder.getInstance().username("john").password("pass").build();

        userRepository.save(user);

        // when
        User userFound = userRepository.findByUsername("john");

        // then
        assertThat(userFound, is(notNullValue()));
        assertThat(userFound.getUsername(), is("john"));
        assertThat(userFound.getPassword(), is("pass"));

    }



    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
    }
}
