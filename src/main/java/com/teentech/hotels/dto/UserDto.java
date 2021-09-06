package com.teentech.hotels.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    private String username;

    private String language;

    private String mail;

    private Long roleId;

    private List<String> rights;

    private long hotelId;

    private String accessToken;
    private String refreshToken;

    private Instant expiryDate;

    private String tokenType = "Bearer";
}
