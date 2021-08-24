package com.teentech.hotels.repository;

import com.teentech.hotels.model.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservations, Long> {

    Reservations findByHotelIdAndRoomNumber(Long hotelId, Long roomNumber);
}
