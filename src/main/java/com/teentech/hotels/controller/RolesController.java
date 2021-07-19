package com.teentech.hotels.controller;

import com.teentech.hotels.model.UserRoles;
import com.teentech.hotels.service.UserRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Optional;
import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin
@RequestMapping("/roles")
public class RolesController {

    @Autowired
    private UserRolesService userRolesService;

    @GetMapping
    public ResponseEntity<UserRoles> getExistentRole(@RequestParam String name){
        try{
        UserRoles Role = userRolesService.getRolebyName(name);
        return new ResponseEntity<UserRoles>(Role, HttpStatus.OK);
    } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping
    public ResponseEntity<UserRoles> addRole(@RequestBody UserRoles Role){
        try {
            userRolesService.addRole(Role);

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity deleteRole(@RequestParam String RoleName) {
        try {
            Optional<UserRoles> role = Optional.ofNullable(userRolesService.getRolebyName(RoleName));
            if (role.isPresent()) {
                userRolesService.deleteRole(role.get());
            }
            return new ResponseEntity(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
