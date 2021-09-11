package com.teentech.hotels.dto;

import javassist.Loader;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto extends SimpleUserDto {

    private String roleName;
    private List<String> rights;

    private String accessToken;
    private String refreshToken;
    private Instant expiryDate;
    private String tokenType = "Bearer";

    @Builder
    public UserDto(String username, String language, String mail, Long roleId, Long hotelId, String roleName, List<String> rights, String accessToken, String refreshToken, Instant expiryDate, String tokenType) {
        super(username, language, mail, roleId, hotelId);
        this.roleName = roleName;
        this.rights = rights;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiryDate = expiryDate;
        this.tokenType = tokenType;
    }
}
