package com.teentech.hotels.dto;

import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDto implements Serializable {

    private Long id;

    private Long hotelId;

    private Long roomNumber;

    private Date startDate;
    private Date endDate;

    private String name;
    private String surname;

    private String passportId;

    private String email;

    private String phoneNumber;

    private Boolean breakfast;
    private Boolean lunch;
    private Boolean dinner;

    private Boolean everydayCleaning;

    private Long checkout;

    private String currency;

    private Boolean checkoutCompleted;
}
