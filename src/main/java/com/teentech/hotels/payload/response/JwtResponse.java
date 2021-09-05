package com.teentech.hotels.payload.response;


import com.teentech.hotels.model.RefreshToken;
import lombok.Getter;
import lombok.Setter;

import com.teentech.hotels.model.UserRole;


@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private RefreshToken refreshToken;
    private String username;
    private String password;
    private String language;
    private String mail;
    private UserRole userRole;
    private Long roleId;
    private long hotelId;

    public JwtResponse(String accessToken, RefreshToken refreshToken, String username, String password, String language,
                       String mail, UserRole userRole, Long roleId, long hotelId) {
        this.token = accessToken;
        this.refreshToken = refreshToken;
        this.username = username;
        this.password = password;
        this.language = language;
        this.mail = mail;
        this.userRole = userRole;
        this.roleId = roleId;
        this.hotelId = hotelId;
    }
}
