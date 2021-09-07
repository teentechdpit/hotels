package com.teentech.hotels.controller;


import com.teentech.hotels.dto.UserDto;
import com.teentech.hotels.model.RefreshToken;
import com.teentech.hotels.model.UserRights;
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
import java.util.List;
import java.util.stream.Collectors;

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
        List<String> rightsNames = userDetails.getUserRole().getRights().stream().map(UserRights::getName).collect(Collectors.toList());

        try {
            return ResponseEntity.ok(new UserDto(userDetails.getUsername(), userDetails.getLanguage(), userDetails.getMail(),
                    userDetails.getRoleId(), userDetails.getUserRole().getName(), rightsNames, userDetails.getHotelId(), jwt, refreshToken.getToken(), refreshToken.getExpiryDate(), "Bearer"));
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
