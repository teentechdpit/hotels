package com.teentech.hotels.controller;

import com.teentech.hotels.dto.UserDto;
import com.teentech.hotels.model.Registration;
import com.teentech.hotels.model.User;
import com.teentech.hotels.dto.UserRegistrationDto;
import com.teentech.hotels.repository.UserRoleRepository;
import com.teentech.hotels.service.RegistrationService;
import com.teentech.hotels.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import java.util.Optional;
import java.util.UUID;


@RestController
@CrossOrigin
@RequestMapping("/users")
@Log4j2
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RegistrationService registrationService;

    @GetMapping
    public ResponseEntity<UserDto> getAuthenticatedUser(@RequestParam String userName, @RequestParam String password) {
        try {
            UserDto authUser = userService.getUserByName(userName, password);
            if (authUser != null) {
                return new ResponseEntity<UserDto>(authUser, HttpStatus.OK);
            }
            return new ResponseEntity("Not authorized", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity("Error. User or password are incorrect", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody UserDto user) {
        try {
            UUID uuid = UUID.randomUUID();
            String uuidAsString = uuid.toString();
            User userToSave = new User();
            userToSave.setUsername(user.getUsername());
            userToSave.setLanguage(user.getLanguage());
            userToSave.setMail(user.getMail());
            userToSave.setRoles(userRoleRepository.findByName(user.getRoleName()));
            userToSave.setPassword("Not_Verified");

            userService.add(userToSave);
            Registration registration = new Registration(uuidAsString, user.getUsername());
            registrationService.add(registration);
            userService.sendEmailForAuth(user.getMail(), uuidAsString);
            return new ResponseEntity(HttpStatus.OK);
        } catch (MessagingException e) {
            log.error("Error while sending mail to user", e);
            return new ResponseEntity("Mail error", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("Error while adding user on DB", e);
            return new ResponseEntity("Error while add user on database", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/confirmation/{uuid}")
    public ModelAndView confirmation(Model model, @PathVariable String uuid) {
        model.addAttribute("newUserInfo", new UserRegistrationDto());
        model.addAttribute("uuid", uuid);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("setPassword");
        return modelAndView;

    }

    @PostMapping("/registration")
    public ModelAndView processRegister(UserRegistrationDto newUserInfo) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newUserInfo.getPassword());

        Optional<Registration> registration = registrationService.getRegistrationById(newUserInfo.getUuid());

        if (registration.isPresent()) {
            User newUser = userService.getUserByName(registration.get().getUsername());
            newUser.setPassword(encodedPassword);
            userService.updateUser(newUser);
            registrationService.delete(registration.get());

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("successfulRegistration");
            return modelAndView;
        }
        return null;
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
            return new ResponseEntity("Error while delete user:" + userName, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PutMapping
    public ResponseEntity updateUser(@RequestBody UserDto userDto) {
        try {
            User user = new User();
            user.setUsername(userDto.getUsername());
            user.setLanguage(userDto.getLanguage());
            user.setMail(userDto.getMail());
            user.setRoles(userRoleRepository.findByName(userDto.getRoleName()));
            userService.updateUser(user);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error while update the user" + userDto.getUsername(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

