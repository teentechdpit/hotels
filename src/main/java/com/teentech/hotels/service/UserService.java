package com.teentech.hotels.service;

import com.teentech.hotels.dto.UserDto;
import com.teentech.hotels.model.User;
import com.teentech.hotels.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

///necesar @sevice
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserDto getUserByName(String userName, String password) {
        Optional<User> user = userRepository.findById(userName);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if (user.isPresent() && bCryptPasswordEncoder.matches(password, user.get().getPassword())) {
            UserDto userDto = new UserDto();
            userDto.setMail(user.get().getMail());
            userDto.setUsername(user.get().getUsername());
            userDto.setRoleName(user.get().getRoles().getName());
            List<String> rights = new ArrayList<>();
            user.get().getRoles().getRights().stream().forEach(r -> rights.add(r.getName()));
            userDto.setRights(rights);
            return  userDto;
        }
        return null;
    }

    public User getUserByName(String userName) {
        Optional<User> user = userRepository.findById(userName);
        return user.get();
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