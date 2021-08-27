package com.teentech.hotels.controller;


import com.teentech.hotels.dto.ReservationDto;
import com.teentech.hotels.model.Bar;
import com.teentech.hotels.model.HotelRooms;
import com.teentech.hotels.model.HotelRoomsPK;
import com.teentech.hotels.model.Reservations;
import com.teentech.hotels.service.BarService;
import com.teentech.hotels.service.HotelRoomsService;
import com.teentech.hotels.service.ReservationService;
import com.teentech.hotels.util.ReservationsConverter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/reservations")
@Log4j2
public class ReservationsController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private BarService barService;

    @Autowired
    private HotelRoomsService hotelRoomsService;

    @GetMapping
    public ResponseEntity<ReservationDto> getReservation(@RequestParam Long hotelId, @RequestParam Long roomNumber) {
        try {
            Reservations reservation = reservationService.getCurrentReservation(hotelId, roomNumber);

            if (reservation != null) {
                ReservationDto reservationDto = ReservationsConverter.convertFromEntityToDto(reservation);
                return new ResponseEntity<>(reservationDto, HttpStatus.OK);
            }
            log.error("Reservation not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Reservation details are incorrect", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Boolean> addReservation(@RequestBody ReservationDto reservation) {
        try {
            Reservations reservationToSave = ReservationsConverter.convertFromDtoToEntity(reservation);

            if (!reservationToSave.getEverydayCleaning()) {
                HotelRoomsPK hotelRoomsPK = new HotelRoomsPK(reservationToSave.getHotelId(), reservationToSave.getRoomNumber());
                HotelRooms room = hotelRoomsService.findHotelRoomById(hotelRoomsPK);

                int noOfDays = (int) ((reservationToSave.getEndDate().getTime() - reservationToSave.getStartDate().getTime()) / (1000 * 60 * 60 * 24));

                Bar bar = new Bar(reservationToSave.getHotelId(), reservationToSave.getRoomNumber(), room.getNoOfPeople() * (noOfDays - 1));

                barService.add(bar);
            }

            reservationService.add(reservationToSave);

            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while adding a reservation in the database", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteReservation(@PathVariable Long id) {
        try {
            Optional<Reservations> reservation = Optional.ofNullable(reservationService.getReservationById(id));

            if (reservation.isPresent()) {
                reservationService.delete(reservation.get());
            }

            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while deleting reservation", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Boolean> updateReservation(@RequestBody ReservationDto reservationDto) {
        try {
            Reservations reservation = ReservationsConverter.convertFromDtoToEntity(reservationDto);

            reservationService.update(reservation);

            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while updating reservation", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
