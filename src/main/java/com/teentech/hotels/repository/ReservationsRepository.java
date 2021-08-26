package com.teentech.hotels.repository;

import com.teentech.hotels.model.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservations, Long> {

    @Query("SELECT r FROM Reservations r WHERE r.hotelId = ?1 AND r.roomNumber = ?2 AND r.startDate <= ?3 AND r.endDate >= ?3")
    Reservations findReservation(Long hotelId, Long roomNumber, Date currentDate);
}
