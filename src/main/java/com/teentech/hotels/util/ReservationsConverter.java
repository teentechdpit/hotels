package com.teentech.hotels.util;

import com.teentech.hotels.dto.ReservationDto;
import com.teentech.hotels.model.Reservations;

public class ReservationsConverter {

    public static Reservations convertFromDtoToEntity(ReservationDto reservationDto) {

        Reservations reservation = new Reservations();

        reservation.setHotelId(reservationDto.getHotelId());

        reservation.setRoomNumber(reservationDto.getRoomNumber());

        reservation.setStartDate(reservationDto.getStartDate());
        reservation.setEndDate(reservationDto.getEndDate());

        reservation.setName(reservationDto.getName());
        reservation.setSurname(reservationDto.getSurname());

        reservation.setPassportId(reservationDto.getPassportId());
        reservation.setEmail(reservationDto.getEmail());
        reservation.setPhone(reservationDto.getPhone());

        reservation.setBreakfast(reservationDto.getBreakfast());
        reservation.setLunch(reservationDto.getLunch());
        reservation.setDinner(reservationDto.getDinner());

        return reservation;
    }

    public static ReservationDto convertFromEntityToDto(Reservations reservation) {

        ReservationDto reservationDto = new ReservationDto();

        reservationDto.setHotelId(reservation.getHotelId());

        reservationDto.setRoomNumber(reservation.getRoomNumber());

        reservationDto.setStartDate(reservation.getStartDate());
        reservationDto.setEndDate(reservation.getEndDate());

        reservationDto.setName(reservation.getName());
        reservationDto.setSurname(reservation.getSurname());

        reservationDto.setPassportId(reservation.getPassportId());
        reservationDto.setEmail(reservation.getEmail());
        reservationDto.setPhone(reservation.getPhone());

        reservationDto.setBreakfast(reservation.getBreakfast());
        reservationDto.setLunch(reservation.getLunch());
        reservationDto.setDinner(reservation.getDinner());

        return reservationDto;
    }
}
