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

    @Query(value = "SELECT * FROM rooms WHERE hotel_id = ?1 AND room_view = ?2 AND no_of_people = ?3 AND type = ?4 AND NOT EXISTS (SELECT * FROM reservations WHERE reservations.room_number = rooms.room_number AND ((end_date > ?5 AND end_date < ?6) OR (start_date > ?5 AND end_date < ?6) OR (start_date < ?5 AND end_date > ?6) OR start_date = ?5 OR end_date = ?6))", nativeQuery = true)
    List<HotelRooms> findAvailableHotelRooms(Long hotelId, String view, int noOfPeople,  String roomType, Date startDate, Date endDate);
}
