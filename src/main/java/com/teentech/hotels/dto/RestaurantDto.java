package com.teentech.hotels.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantDto implements Serializable
{
    private long hotelId;

    private long roomNumber;

    private Date lastBreakfastDate;

    private Date lastLunchDate;

    private Date lastDinnerDate;
}
