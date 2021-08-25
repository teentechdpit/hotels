package com.teentech.hotels.repository;

import com.teentech.hotels.model.HotelRooms;
import com.teentech.hotels.model.HotelRoomsPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;


@Repository
public interface HotelRoomsRepository  extends JpaRepository<HotelRooms, HotelRoomsPK> {

    @Query("SELECT room FROM HotelRooms room WHERE room.hotelId = ?1 AND room.view = ?2 AND room.noOfPeople = ?5 AND MUST NOT EXISTS (SELECT reservation FROM Reservations WHERE reservation.startDate > ?3 OR reservation.endDate < ?4)")
    List<HotelRooms> findAvailableHotelRooms(Long hotelId, String View, Date startDate, Date endDate, int noOfPeople);
}
