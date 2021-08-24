package com.teentech.hotels.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelDto implements Serializable {

    private Long id;

    private String name;

    private String country;

    private String city;

    private int stars;

    private String mail;

    private String phone;

    private boolean paid;
}
