package com.teentech.hotels.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelRoomsPK implements Serializable {

    @Column(name = "room_number")
    private long roomNumber;

    @Column(name = "hotel_id")
    private long hotelId;
}
