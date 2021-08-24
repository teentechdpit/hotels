package com.teentech.hotels.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@IdClass(HotelRoomsPK.class)
@Table(name = "restaurant")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant implements Serializable {

    @Id
    @Column(name = "hotel_id")
    private long hotelId;

    @Id
    @Column(name = "room_number")
    private long roomNumber;

    @Column(name = "last_breakfast_date")
    private Date lastBreakfastDate;

    @Column(name = "last_lunch_date")
    private Date lastLunchDate;

    @Column(name = "last_dinner_date")
    private Date lastDinnerDate;
}
