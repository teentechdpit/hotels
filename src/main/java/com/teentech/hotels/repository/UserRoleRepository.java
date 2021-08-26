package com.teentech.hotels.repository;

import com.teentech.hotels.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByName(String roleName);
}
