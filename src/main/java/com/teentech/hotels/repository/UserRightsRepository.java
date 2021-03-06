package com.teentech.hotels.repository;

import com.teentech.hotels.model.UserRights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRightsRepository extends JpaRepository<UserRights, Long> {

}
