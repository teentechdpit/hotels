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
public class HotelRoomsDto implements Serializable {

    private long roomNumber;

    private long hotelId;

    private String type;

    private String roomView;

    private int noOfPeople;
}
