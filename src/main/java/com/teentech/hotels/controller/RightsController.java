package com.teentech.hotels.controller;

import com.teentech.hotels.model.UserRights;
import com.teentech.hotels.service.UserRightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Optional;
import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin
@RequestMapping("/rights")
public class RightsController {

    @Autowired
    private UserRightsService userRightsService;

    @GetMapping
    public ResponseEntity<UserRights> getExistentRight(@RequestParam String Name){
        try{
            UserRights Rights = userRightsService.findUserRightsByName(Name);
            return new ResponseEntity<UserRights>(Rights, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping
    public ResponseEntity<UserRights> addRight(@RequestBody UserRights Rights){
        try {
            userRightsService.addRights(Rights);

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity deleteRight(@RequestParam String RoleName) {
        try {
            Optional<UserRights> rights = Optional.ofNullable(userRightsService.findUserRightsByName(RoleName));
            if (rights.isPresent()) {
                userRightsService.deleteRights(rights.get());
            }
            return new ResponseEntity(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}