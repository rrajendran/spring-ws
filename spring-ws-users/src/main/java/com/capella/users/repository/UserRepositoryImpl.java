package com.capella.users.repository;

import com.capella.users.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author ramesh
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User save(User user) {
       return entityManager.merge(user);
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        Query query = entityManager.createNamedQuery("find.by.username");
        query.setParameter("username", username);

       return (User) query.getSingleResult();
    }

    @Override
    @Transactional
    public void deleteAll() {
        //entityManager.createNativeQuery("delete from user")
    }
}
