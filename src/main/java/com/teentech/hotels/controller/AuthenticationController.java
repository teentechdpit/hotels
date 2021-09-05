package com.teentech.hotels.controller;


import javax.validation.Valid;

import com.teentech.hotels.payload.request.TokenRefreshRequest;
import com.teentech.hotels.payload.response.TokenRefreshResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.teentech.hotels.security.jwt.JwtUtils;
import com.teentech.hotels.service.RefreshTokenService;
import com.teentech.hotels.payload.request.LoginRequest;
import com.teentech.hotels.model.RefreshToken;
import com.teentech.hotels.payload.response.JwtResponse;
import com.teentech.hotels.security.service.UserDetailsImpl;


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
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(authentication);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getUsername());

        return ResponseEntity.ok(new JwtResponse(jwt, refreshToken, userDetails.getUsername(), userDetails.getPassword(),
                userDetails.getLanguage(), userDetails.getMail(), userDetails.getUserRole(), userDetails.getRoleId(), userDetails.getHotelId()));
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
