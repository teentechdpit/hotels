package com.teentech.hotels.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(HotelRoomsPK.class)
@Table(name = "rooms")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelRooms implements Serializable {

    @Id
    @Column(name = "room_number")
    private long roomNumber;

    @Id
    @Column(name = "hotel_id")
    private long hotelId;

    private String type;

    @Column(name = "room_view")
    private String roomView;

    @Column(name = "no_of_people")
    private int noOfPeople;
}
