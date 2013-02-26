package com.capella.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("springUserService")
public class SpringUserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		com.capella.users.User findByUsername = userRepository.findByUsername(username);
		if(findByUsername != null){
			User user = new User(findByUsername.getUsername(), findByUsername.getPassword(), null);
			return user;
		}
		return null;
	}

}
