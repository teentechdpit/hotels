package com.teentech.hotels.service;

import com.teentech.hotels.model.UserRoles;
import com.teentech.hotels.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRolesService {

    @Autowired
    public UserRoleRepository userRoleRepository;

    public UserRoles getRolebyName(String name){
        return userRoleRepository.findByName(name);
    }

    public void addRole(UserRoles role){
        userRoleRepository.save(role);
    }

    public void deleteRole(UserRoles role){
        userRoleRepository.delete(role);
    }
}
