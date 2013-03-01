package com.capella.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.capella.entity.User;

@Repository ("userService")
public class UserService{
	@Autowired
	private MongoOperations mongoOperations;
	
	public void save(User user){
		mongoOperations.save(user);
	}
	
	public User getUser(String username){
		Query query = new Query(Criteria.where("username").is(username));
		return mongoOperations.findOne(query, User.class);
	}

	public void delete(String username) {
		Query query = new Query(Criteria.where("username").is(username));
		mongoOperations.remove(query, User.class);
	}
}
