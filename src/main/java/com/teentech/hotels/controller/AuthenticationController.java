package com.teentech.hotels.controller;


import com.teentech.hotels.dto.UserDto;
import com.teentech.hotels.model.RefreshToken;
import com.teentech.hotels.model.UserRights;
import com.teentech.hotels.model.UserRole;
import com.teentech.hotels.payload.request.LoginRequest;
import com.teentech.hotels.security.jwt.JwtUtils;
import com.teentech.hotels.security.service.UserDetailsImpl;
import com.teentech.hotels.service.RefreshTokenService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Log4j2
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RefreshTokenService refreshTokenService;

    @PostMapping("/signin")
    public ResponseEntity<UserDto> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(authentication);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getUsername());

        ArrayList<String> roleNames = new ArrayList<>();
        for (UserRights userRight: userDetails.getUserRole().getRights()) {
            roleNames.add(userRight.getName());
        }

        try {
            return ResponseEntity.ok(new UserDto(userDetails.getUsername(), userDetails.getLanguage(), userDetails.getMail(),
                    userDetails.getRoleId(), roleNames, userDetails.getHotelId(), jwt, refreshToken.getToken(), refreshToken.getExpiryDate(), null));
        } catch (Exception e) {
            log.error("Error while creating token", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping("/refreshtoken")
//    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
//
//        String requestRefreshToken = request.getRefreshToken();
//
//        RefreshTokenService refreshTokenService = new RefreshTokenService();
//
//        return refreshTokenService.findByToken(requestRefreshToken)
//                .map(refreshTokenService::verifyExpiration) !!! EROARE AICI "Unhandled exception: java.lang.Exception"
//                .map(RefreshToken::getUser)
//                .map(user -> {
//                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
//                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
//                }).orElseThrow(() -> new Exception("Refresh token not in database!"));
//    }
}
