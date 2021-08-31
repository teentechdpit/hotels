package com.teentech.hotels.controller;


import com.teentech.hotels.dto.ReservationDto;
import com.teentech.hotels.model.*;
import com.teentech.hotels.service.*;
import com.teentech.hotels.util.ReservationsConverter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.sql.Date;
import java.util.Arrays;
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

    @Autowired
    private HotelService hotelService;

    @Autowired
    private DocumentService documentService;

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

            Optional<Hotel> hotel = hotelService.getHotelById(reservation.getHotelId());

            if (!reservationToSave.getEverydayCleaning()) {
                HotelRoomsPK hotelRoomsPK = new HotelRoomsPK(reservationToSave.getHotelId(), reservationToSave.getRoomNumber());
                HotelRooms room = hotelRoomsService.findHotelRoomById(hotelRoomsPK);

                int noOfDays = (int) ((reservationToSave.getEndDate().getTime() - reservationToSave.getStartDate().getTime()) / (1000 * 60 * 60 * 24));
                int noOfDrinks = room.getNoOfPeople() * (noOfDays - 1);

                Bar bar = new Bar(reservationToSave.getHotelId(), reservationToSave.getRoomNumber(), room.getNoOfPeople() * (noOfDays - 1));

                barService.add(bar);

                String htmlText = "<h1 style=\"text-align:center;  font-family:'FranklinGothicMedium','ArialNarrow',Arial,sans-serif;" +
                        "font-weight:100;font-size:2vw;color:#0e77de;margin-top:3vw;margin-bottom:3vw;\"> &#127867; This is your Drinking Status &#127867;</h1>" +
                        "<p style=\"text-align:center;font-family:'GillSans','GillSansMT',Calibri,'TrebuchetMS',sans-serif;font-size:1vw;\">" +
                        "Congratulations for choosing our offer! &#128079; You have no less than <b>" + noOfDrinks + " drinks</b> to savor. &#128523;</p>";

                BodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent(htmlText, "text/html");

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);

                MailService.send(System.getenv("EMAIL_ADDRESS"), System.getenv("EMAIL_PASSWORD"), reservationToSave.getEmail(), Arrays.asList(hotel.get().getMail()),"Drinks status", multipart);
            }

            long millis = System.currentTimeMillis();
            Date todayDate = new Date(millis); //example: 2021-08-27

            try {
                documentService.updateTemplateDoc("src\\main\\resources\\templates\\ReservationTemplate.docx",
                        "src\\main\\resources\\templates\\ReservationOutput.docx", todayDate, reservation, hotel.orElse(null));
            } catch (Exception e) {
                log.error("Error at updating reservation template", e);
            }

            MimeBodyPart textBodyPart = new MimeBodyPart();
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();

            try {
                textBodyPart.setText("Thank you for making a reservation using Hotelliste! \uD83D\uDE00");
                attachmentBodyPart.attachFile(new File("src\\main\\resources\\templates\\ReservationOutput.docx"));

                multipart.addBodyPart(textBodyPart);
                multipart.addBodyPart(attachmentBodyPart);
            } catch (Exception e) {
                log.error("Error at creating the body parts of the reservation confirmation email", e);
            }

            try {
                MailService.send(System.getenv("EMAIL_ADDRESS"), System.getenv("EMAIL_PASSWORD"),
                        reservationToSave.getEmail(), Arrays.asList(hotel.get().getMail()), "Reservation Confirmed", multipart);
            } catch (Exception e) {
                log.error("Error at sending the reservation confirmation email", e);
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

            System.out.println(1);
            Optional<Reservations> reservation = Optional.ofNullable(reservationService.getReservationById(id));

            System.out.println(1);

            if (reservation.isPresent()) {
                System.out.println(reservation.get().getId());
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
