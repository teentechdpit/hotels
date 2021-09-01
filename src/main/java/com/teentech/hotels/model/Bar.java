package com.teentech.hotels.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(HotelRoomsPK.class)
@Table(name = "bar")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bar implements Serializable {

    @Id
    @Column(name = "hotel_id")
    private long hotelId;

    @Id
    @Column(name = "room_number")
    private long roomNumber;

    @Column(name = "no_of_drinks")
    private int noOfDrinks;
}