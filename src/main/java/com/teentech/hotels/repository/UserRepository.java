package com.teentech.hotels.repository;

import com.teentech.hotels.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {


    User findByUsernameAndPassword(String username, String password);

}
