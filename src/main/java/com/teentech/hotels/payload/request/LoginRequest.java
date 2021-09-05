package com.teentech.hotels.payload.request;


import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

import com.teentech.hotels.model.UserRole;


@Getter
@Setter
public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String language;

    @NotBlank
    private String mail;

    @NotBlank
    private UserRole userRole;

    @NotBlank
    private Long roleId;

    @NotBlank
    private long hotelId;
}
