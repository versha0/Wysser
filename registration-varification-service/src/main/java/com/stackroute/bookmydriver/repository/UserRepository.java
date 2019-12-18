package com.stackroute.bookmydriver.repository;

import com.stackroute.bookmydriver.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);

    User findByConfirmationToken(String confirmationToken);
}
