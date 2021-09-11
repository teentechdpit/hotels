package com.teentech.hotels.service;


import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import com.teentech.hotels.security.RefreshTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teentech.hotels.model.RefreshToken;
import com.teentech.hotels.repository.RefreshTokenRepository;
import com.teentech.hotels.repository.UserRepository;


@Service
public class RefreshTokenService {
    @Value("${teentech.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(String username) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUsername(username);
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token)  {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RefreshTokenException(String.format("Refresh token %s was expired. Please make a new sign-in request!", token.getToken()));
        }

        return token;
    }

    @Transactional
    public int deleteByUsername(String username) {
        return refreshTokenRepository.deleteByUsername(username);
    }
}