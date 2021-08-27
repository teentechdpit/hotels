package com.teentech.hotels.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservations implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "hotel_id")
    private Long hotelId;

    @Column(name = "room_number")
    private Long roomNumber;

    private Date startDate;

    private Date endDate;

    private String name;

    private String surname;

    private Long passportId;

    private String email;

    @Column(name = "phone")
    private String phoneNumber;

    private Boolean breakfast;
    private Boolean lunch;
    private Boolean dinner;

    @Column(name = "everyday_cleaning")
    private Boolean everydayCleaning;
}
