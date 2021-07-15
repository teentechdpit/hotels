package com.teentech.hotels.controller;

import com.teentech.hotels.model.User;
import com.teentech.hotels.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<User> getAuthenticatedUser(@RequestParam String userName, @RequestParam String password) {
        try {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            User authUser = userService.getUserById(userName);
            if (authUser != null && bCryptPasswordEncoder.matches(password, authUser.getPassword())) {
                return new ResponseEntity<User>(authUser, HttpStatus.OK);
            }
            return new ResponseEntity("Not authorized", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody User user) {
        try {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            userService.add(user);
            return new ResponseEntity(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
