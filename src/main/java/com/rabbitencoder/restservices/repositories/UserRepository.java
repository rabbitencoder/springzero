package com.rabbitencoder.restservices.repositories;

import com.rabbitencoder.restservices.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author rahul
 * @date 5/21/2024 12:10 PM
 * -
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
