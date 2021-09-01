package com.teentech.hotels.service;

import com.teentech.hotels.model.Reservations;
import com.teentech.hotels.repository.ReservationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationsRepository reservationRepository;

    public List<Reservations> getAll() { return reservationRepository.findAll(); }

    public void add(Reservations reservation) {
        reservationRepository.save(reservation);
    }

    public void update(Reservations reservation) {
        reservationRepository.save(reservation);
    }

    public void delete(Reservations reservation) {
        reservationRepository.delete(reservation);
    }

    public Reservations getReservationById(Long id) {
        System.out.println(2);
        Optional<Reservations> reservation = reservationRepository.findById(id);
        System.out.println(2);
        return reservation.orElse(null);
    }

    public Reservations getCurrentReservation(Long hotelId, Long roomNumber) {
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
        return reservationRepository.findReservation(hotelId, roomNumber, currentDate);
    }

}
