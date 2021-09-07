package com.teentech.hotels.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TokenRefreshRequest {
    @NotBlank(message = "Refresh Token may no be null!")
    private String refreshToken;
}