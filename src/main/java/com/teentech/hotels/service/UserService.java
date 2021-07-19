package com.teentech.hotels.service;

import com.teentech.hotels.model.User;
import com.teentech.hotels.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

///necesar @sevice
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByName(String userName) {
        return userRepository.getOne(userName);
    }

    public User getAuthenticatedUser(String userName, String password) {
        return userRepository.findByUsernameAndPassword(userName, password);
    }

    public void add(User user) {
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public void updateUser(User user){
        userRepository.save(user);
    }
}