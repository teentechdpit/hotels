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

    private Long hotelId;

    private Long roomNumber;

    private Date startDate;
    private Date endDate;

    private String name;
    private String surname;

    private Long passportId;

    private String email;

    private String phoneNumber;

    private Boolean breakfast;
    private Boolean lunch;
    private Boolean dinner;
}
