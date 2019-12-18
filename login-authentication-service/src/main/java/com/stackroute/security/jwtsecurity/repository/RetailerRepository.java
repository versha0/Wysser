package com.stackroute.security.jwtsecurity.repository;

import com.stackroute.security.jwtsecurity.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetailerRepository extends CrudRepository<User, Long> {

    public User findUserByEmail(String email);


}
