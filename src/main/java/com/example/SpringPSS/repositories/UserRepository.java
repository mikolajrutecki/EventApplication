package com.example.SpringPSS.repositories;

import com.example.SpringPSS.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
    boolean existsByUsername(String username);

    User findUserById(int id);
}
