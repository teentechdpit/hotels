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
@Table(name = "clean")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Clean implements Serializable {

    @Id
    @Column(name = "hotel_id")
    private long hotelId;

    @Id
    @Column(name = "room_number")
    private long roomNumber;

    @Column(name = "last_clean_day")
    private Date lastCleanDay;

    @Column(name = "last_change_lingerie")
    private Date lastChangeLingerie;

    @Column(name = "last_change_towels")
    private Date lastChangeTowels;
}
