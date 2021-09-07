package com.teentech.hotels.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemDto implements Serializable {

    private int hotelId;

    private String nameOfEntry;

    private int entryTypeId;

    private int price;

    private String currency;
}
