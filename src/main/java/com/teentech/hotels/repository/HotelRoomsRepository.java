package com.teentech.hotels.repository;

import com.teentech.hotels.model.HotelRooms;
import com.teentech.hotels.model.HotelRoomsPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRoomsRepository  extends JpaRepository<HotelRooms, HotelRoomsPK> {

}
