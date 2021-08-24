package com.teentech.hotels.service;

import com.teentech.hotels.dto.ReservationDto;
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

    public ReservationDto getReservationByHotelIdAndRoomNumber(Long hotelId, Long roomNumber) {
        Optional<Reservations> currentReservation = Optional.ofNullable(reservationRepository.findByHotelIdAndRoomNumber(hotelId, roomNumber));

        if (currentReservation.isPresent()) {
            ReservationDto reservationDto = new ReservationDto();

            reservationDto.setHotelId(currentReservation.get().getHotelId());

            reservationDto.setRoomNumber(currentReservation.get().getRoomNumber());

            reservationDto.setStartDate(currentReservation.get().getStartDate());
            reservationDto.setEndDate(currentReservation.get().getEndDate());

            reservationDto.setName(currentReservation.get().getName());
            reservationDto.setSurname(currentReservation.get().getSurname());

            reservationDto.setPassportId(currentReservation.get().getPassportId());

            reservationDto.setEmail(currentReservation.get().getEmail());

            reservationDto.setPhoneNumber(currentReservation.get().getPhone());

            return reservationDto;
        }

        return null;
    }

    public Reservations getReservation(Long hotelId, Long roomNumber) {
        Optional<Reservations> reservation = Optional.ofNullable(reservationRepository.findByHotelIdAndRoomNumber(hotelId, roomNumber));
        return reservation.orElse(null);
    }

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
        Optional<Reservations> reservation = reservationRepository.findById(id);
        return reservation.orElse(null);
    }
}