package com.teentech.hotels.controller;


import com.teentech.hotels.dto.ReservationDto;
import com.teentech.hotels.model.Reservations;
import com.teentech.hotels.service.ReservationService;
import com.teentech.hotels.util.ReservationsConverter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/reservations")
@Log4j2
public class ReservationsController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public ResponseEntity<ReservationDto> getReservation(@RequestParam Long hotelId, @RequestParam Long roomNumber) {
        try {
            Reservations reservation = reservationService.getCurrentReservation(hotelId, roomNumber);

            if (reservation != null) {
                ReservationDto reservationDto = ReservationDto.builder().hotelId(reservation.getHotelId()).roomNumber(reservation.getRoomNumber()).startDate(reservation.getStartDate()).endDate(reservation.getEndDate()).name(reservation.getName()).passportId(reservation.getPassportId()).email(reservation.getEmail()).phoneNumber(reservation.getPhoneNumber()).breakfast(reservation.getBreakfast()).lunch(reservation.getLunch()).dinner(reservation.getDinner()).build();
                return new ResponseEntity<ReservationDto>(reservationDto, HttpStatus.OK);
            }
            return new ResponseEntity("Not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Error. Reservation details are incorrect", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity addReservation(@RequestBody ReservationDto reservation) {
        try {
            Reservations reservationToSave = ReservationsConverter.convertFromDtoToEntity(reservation);

            reservationService.add(reservationToSave);

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while adding a reservation in the database", e);
            return new ResponseEntity("Error while adding a reservation in the database", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteReservation(@PathVariable Long id) {
        try {
            Optional<Reservations> reservation = Optional.ofNullable(reservationService.getReservationById(id));

            if (reservation.isPresent()) {
                reservationService.delete(reservation.get());
            }

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error while deleting reservation", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity updateReservation(@RequestBody Reservations reservation) {
        try {
            reservationService.update(reservation);

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error while updating reservation", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
