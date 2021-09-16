package com.teentech.hotels.controller;


import com.teentech.hotels.dto.EmailDto;
import com.teentech.hotels.dto.ReservationDto;
import com.teentech.hotels.dto.ReservationSignatureDto;
import com.teentech.hotels.model.*;
import com.teentech.hotels.service.*;
import com.teentech.hotels.util.ReservationsConverter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

@RestController
@CrossOrigin
@RequestMapping("/reservations")
@Log4j2
@SecurityRequirement(name = "bearerAuth")
public class ReservationsController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private BarService barService;

    @Autowired
    private HotelRoomsService hotelRoomsService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private MailService mailService;

    @GetMapping
    public ResponseEntity<ReservationDto> getReservation(@RequestParam Long hotelId, @RequestParam Long roomNumber) {
        try {
            Reservations reservation = reservationService.getCurrentReservation(hotelId, roomNumber);

            if (reservation != null) {
                ReservationDto reservationDto = ReservationsConverter.convertFromEntityToDto(reservation);
                return new ResponseEntity<>(reservationDto, HttpStatus.OK);
            }
            log.error("Reservation not found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error("Reservation details are incorrect", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id")
    public ResponseEntity<Long> getId(@RequestParam Long hotelId, @RequestParam Long roomNumber) {
        try {
            Reservations reservation = reservationService.getCurrentReservation(hotelId, roomNumber);

            if(reservation == null) {
                log.error("Reservation not found on get by id for room number {}", roomNumber);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Long id = reservation.getId();
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while getting reservation from db", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/checkout")
    public ResponseEntity<Boolean> checkout(@RequestBody HotelRoomsPK hotelRoomsPK) {
        try {
            Long hotelId = hotelRoomsPK.getHotelId();
            Long roomNumber = hotelRoomsPK.getRoomNumber();

            Reservations reservation = reservationService.getCurrentReservation(hotelId, roomNumber);

            if(reservation == null) {
                log.error("Reservation not found for checkout for room {}", roomNumber );
                return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NO_CONTENT);
            }

            if (reservation.getCheckoutCompleted()) {
                log.error("Checkout already completed!");
                return new ResponseEntity<>(Boolean.FALSE, HttpStatus.ALREADY_REPORTED);
            }

            reservation.setCheckoutCompleted(true);
            reservationService.update(reservation);

            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while completing checkout", e);
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Boolean> addReservation(@RequestBody ReservationSignatureDto reservation) {
        try {
            Reservations reservationToSave = ReservationsConverter.convertFromSignatureToNormal(reservation);

            reservationToSave.setCheckoutCompleted(false);

            Optional<Hotel> hotel = hotelService.getHotelById(reservation.getHotelId());
            if(!hotel.isPresent()) {
                return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NO_CONTENT);
            }

            if (Boolean.FALSE.equals(reservationToSave.getEverydayCleaning())) {
                HotelRoomsPK hotelRoomsPK = new HotelRoomsPK(reservationToSave.getRoomNumber(), reservationToSave.getHotelId());
                HotelRooms room = hotelRoomsService.findHotelRoomById(hotelRoomsPK);

                long noOfDays = DAYS.between(reservationToSave.getStartDate().toLocalDate(), reservationToSave.getEndDate().toLocalDate());
                long noOfDrinks = room.getNoOfPeople() * (noOfDays - 1);

                Bar bar = new Bar(reservationToSave.getHotelId(), reservationToSave.getRoomNumber(), (int) noOfDrinks);

                barService.add(bar);

                String htmlText = "<h1 style=\"text-align:center;  font-family:'FranklinGothicMedium','ArialNarrow',Arial,sans-serif;" +
                        "font-weight:100;font-size:2vw;color:#0e77de;margin-top:3vw;margin-bottom:3vw;\"> &#127867; This is your Drinking Status &#127867;</h1>" +
                        "<p style=\"text-align:center;font-family:'GillSans','GillSansMT',Calibri,'TrebuchetMS',sans-serif;font-size:1vw;\">" +
                        "Congratulations for choosing our offer! &#128079; You have no less than <b>" + noOfDrinks + " drinks</b> to savor. &#128523;</p>";

                EmailDto emailDto = EmailDto.builder().to(reservationToSave.getEmail()).subject("Drinks status").content(htmlText).cc(Arrays.asList(hotel.get().getMail())).build();
                mailService.send(emailDto);
            }

            ByteArrayOutputStream out = documentService.updateTemplateDoc(reservation, hotel.orElse(null));

            EmailDto emailDto = EmailDto.builder().to(reservationToSave.getEmail()).subject("Reservation Confirmed").content("See attachment").cc(Arrays.asList(hotel.get().getMail())).attachmentFile(out).build();


            mailService.send(emailDto);

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
