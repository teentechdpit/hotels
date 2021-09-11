package com.teentech.hotels.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class RefreshTokenException extends RuntimeException {

    public RefreshTokenException(String errorMessage) {
        super(errorMessage);
    }
}
