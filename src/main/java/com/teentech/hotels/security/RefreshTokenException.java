package com.teentech.hotels.security;

public class RefreshTokenException extends Exception {

    public RefreshTokenException(String errorMessage) {
        super(errorMessage);
    }
}
