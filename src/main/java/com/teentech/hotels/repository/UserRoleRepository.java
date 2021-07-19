package com.teentech.hotels.repository;


import com.teentech.hotels.model.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoles,String>{

    UserRoles findByName(String Name);
}
