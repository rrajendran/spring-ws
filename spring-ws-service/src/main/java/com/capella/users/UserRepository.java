package com.capella.users;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
  User findByUsernameAndPassword(String username, String password);
  User findByUsername(String username);
}