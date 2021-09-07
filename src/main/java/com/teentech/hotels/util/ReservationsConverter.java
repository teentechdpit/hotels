package com.teentech.hotels.util;

import com.teentech.hotels.dto.ReservationDto;
import com.teentech.hotels.dto.ReservationSignatureDto;
import com.teentech.hotels.model.Reservations;
import lombok.experimental.UtilityClass;

@UtilityClass
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
        reservation.setPhoneNumber(reservationDto.getPhoneNumber());
        reservation.setBreakfast(reservationDto.getBreakfast());
        reservation.setLunch(reservationDto.getLunch());
        reservation.setDinner(reservationDto.getDinner());
        reservation.setEverydayCleaning(reservationDto.getEverydayCleaning());
        reservation.setCheckout(reservationDto.getCheckout());
        reservation.setCurrency(reservationDto.getCurrency());
        reservation.setCheckoutCompleted(reservationDto.getCheckoutCompleted());

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
        reservationDto.setPhoneNumber(reservation.getPhoneNumber());

        reservationDto.setBreakfast(reservation.getBreakfast());
        reservationDto.setLunch(reservation.getLunch());
        reservationDto.setDinner(reservation.getDinner());

        reservationDto.setEverydayCleaning(reservation.getEverydayCleaning());

        reservationDto.setCheckout(reservation.getCheckout());

        reservationDto.setCurrency(reservation.getCurrency());

        reservationDto.setCheckoutCompleted(reservation.getCheckoutCompleted());

        return reservationDto;
    }

    public static Reservations convertFromSignatureToNormal (ReservationSignatureDto reservationSignatureDto) {
        Reservations reservation = new Reservations();

        reservation.setHotelId(reservationSignatureDto.getHotelId());
        reservation.setRoomNumber(reservationSignatureDto.getRoomNumber());
        reservation.setStartDate(reservationSignatureDto.getStartDate());
        reservation.setEndDate(reservationSignatureDto.getEndDate());
        reservation.setName(reservationSignatureDto.getName());
        reservation.setSurname(reservationSignatureDto.getSurname());
        reservation.setPassportId(reservationSignatureDto.getPassportId());
        reservation.setEmail(reservationSignatureDto.getEmail());
        reservation.setPhoneNumber(reservationSignatureDto.getPhoneNumber());
        reservation.setBreakfast(reservationSignatureDto.getBreakfast());
        reservation.setLunch(reservationSignatureDto.getLunch());
        reservation.setDinner(reservationSignatureDto.getDinner());
        reservation.setEverydayCleaning(reservationSignatureDto.getEverydayCleaning());
        reservation.setCheckout(reservationSignatureDto.getCheckout());
        reservation.setCurrency(reservationSignatureDto.getCurrency());

        return reservation;
    }
}
