package com.teentech.hotels.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItemForPageDto implements Serializable {

    private int hotelId;

    private String nameOfEntry;

    private String entryType;

    private int price;

    private String currency;
}
