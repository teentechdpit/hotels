package com.teentech.hotels.controller;

import com.teentech.hotels.dto.RoleDto;
import com.teentech.hotels.service.UserRoleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/roles")
@Log4j2
@SecurityRequirement(name = "bearerAuth")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        try {
            List<RoleDto> roles = userRoleService.getAllRoles();
            if (!roles.isEmpty()) {
                return new ResponseEntity<>(roles, HttpStatus.OK);
            }
            log.error("There are no roles in the DB");
        } catch( Exception e ) {
            log.error("Error while getting roles from DB", e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
