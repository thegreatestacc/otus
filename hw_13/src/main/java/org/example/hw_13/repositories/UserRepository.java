package org.example.hw_13.repositories;

import org.example.hw_13.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<User, Long> {

    @Query("select u from User u where userName = ?1")
    User findUserByUserName(String userName);
}
