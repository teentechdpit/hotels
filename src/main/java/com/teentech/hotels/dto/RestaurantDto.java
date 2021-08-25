package com.teentech.hotels.dto;

import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantDto implements Serializable
{
    private Long reservationId;

    private Date lastBreakfastDate;

    private Date lastLunchDate;

    private Date lastDinnerDate;
}
