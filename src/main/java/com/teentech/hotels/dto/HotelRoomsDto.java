package com.teentech.hotels.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelRoomsDto implements Serializable {

    private long roomNumber;

    private long hotelId;

    private String type;

    private String roomView;

    private int noOfPeople;
}
