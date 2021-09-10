package com.teentech.hotels.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleUserDto implements Serializable {
    private String username;

    private String language;

    private String mail;

    private Long roleId;

    private Long hotelId;
}
