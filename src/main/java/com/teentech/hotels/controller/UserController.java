package com.teentech.hotels.controller;

import com.teentech.hotels.dto.UserDto;
import com.teentech.hotels.model.User;
import com.teentech.hotels.repository.HotelRepository;
import com.teentech.hotels.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.teentech.hotels.repository.UserRoleRepository;

import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @GetMapping
    public ResponseEntity<UserDto> getAuthenticatedUser(@RequestParam String userName, @RequestParam String password) {
        try {
            UserDto authUser = userService.getUserByName(userName, password);
            if (authUser != null ) {
                return new ResponseEntity<UserDto>(authUser, HttpStatus.OK);
            }
            return new ResponseEntity("Not authorized", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody UserDto user) {
        try {
//            System.out.println(System.getenv("EMAIL_ADRESS")); nu le salva de aceaia le-am modiificat
//            System.out.println(System.getenv("EMAIL_PASSWORD"));
            userService.sendEmailForAuth(System.getenv("EMAIL_PASSWORD"),System.getenv("EMAIL_PASSWORD"),user.getMail());
            User userToSave = new User();
            userToSave.setUsername(user.getUsername());
            userToSave.setLanguage(user.getLanguage());
            userToSave.setMail(user.getMail());
            userToSave.setRoles(userRoleRepository.findByName(user.getRoleName()));
            userToSave.setPassword("Not_Verified");

            userService.add(userToSave);
            return new ResponseEntity(HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/confirmation")
    public ModelAndView confirmation(Model model) {
        model.addAttribute("user", new User());

        try{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("setPassword");
        return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        User user2 = userService.getUserByName(user.getUsername());
        System.out.println(user2.getPassword());
        user2.setPassword(encodedPassword);
        userService.updateUser(user2);

        return "register_success";
    }

    @DeleteMapping
    public ResponseEntity deleteUser(@RequestParam String userName) {
        try {
            Optional<User> user = Optional.ofNullable(userService.getUserByName(userName));
            if (user.isPresent()) {
                userService.deleteUser(user.get());
            }
            return new ResponseEntity(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PutMapping
    public ResponseEntity updateUser(@RequestBody User user) {
        try{
            userService.updateUser(user);

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

