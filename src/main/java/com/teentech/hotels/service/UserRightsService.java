package com.teentech.hotels.service;

import com.teentech.hotels.model.UserRights;
import com.teentech.hotels.repository.UserRightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserRightsService {

    @Autowired
    public UserRightsRepository userRightsRepository;

    public UserRights findUserRightsByName(String Name)
    {
        return userRightsRepository.findByName(Name);
    }

    public void addRights(UserRights Rights){
        userRightsRepository.save(Rights);
    }

    public void deleteRights(UserRights Rights){
        userRightsRepository.delete(Rights);
    }
}
