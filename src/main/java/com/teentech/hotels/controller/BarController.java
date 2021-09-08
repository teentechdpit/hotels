package com.teentech.hotels.controller;

import com.teentech.hotels.dto.BarDto;
import com.teentech.hotels.dto.EmailDto;
import com.teentech.hotels.model.Bar;
import com.teentech.hotels.model.HotelRoomsPK;
import com.teentech.hotels.model.Reservations;
import com.teentech.hotels.service.BarService;
import com.teentech.hotels.service.MailService;
import com.teentech.hotels.service.ReservationService;
import com.teentech.hotels.util.BarConverter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/bar")
@Log4j2
@SecurityRequirement(name = "bearerAuth")
public class BarController {

    @Autowired
    private BarService barService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private MailService mailService;

    @GetMapping
    public ResponseEntity<BarDto> getBarById(@RequestParam long hotelId, @RequestParam long roomNumber) {
        try {
            HotelRoomsPK hotelRoomsPK = HotelRoomsPK.builder().hotelId(hotelId).roomNumber(roomNumber).build();
            Bar bar = barService.findBarById(hotelRoomsPK);
            if (bar == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            BarDto barDto = BarConverter.convertFromEntityToDto(bar);
            return new ResponseEntity<>(barDto, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while getting bar info", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Boolean> updateBar(@RequestBody BarDto barDto) {
        try {
            Bar bar = BarConverter.convertFromDtoToEntity(barDto);
            barService.update(bar);


            String htmlText = "<h1 style=\"text-align:center;  font-family:'FranklinGothicMedium','ArialNarrow',Arial,sans-serif;" +
                    "font-weight:100;font-size:2vw;color:#0e77de;margin-top:3vw;margin-bottom:3vw;\"> &#127867; This is your Drinking Status &#127867;</h1>" +
                    "<p style=\"text-align:center;font-family:'GillSans','GillSansMT',Calibri,'TrebuchetMS',sans-serif;font-size:1vw;\">" +
                    "You have <b>" + bar.getNoOfDrinks() + " more drinks</b> to savor. &#128513;</p>";

            Reservations reservation = reservationService.getCurrentReservation(bar.getHotelId(), bar.getRoomNumber());
            EmailDto emailDto = EmailDto.builder().to(reservation.getEmail()).subject("Drinks status").content(htmlText).build();

            mailService.send(emailDto);

            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while updating bar info", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteClean(@RequestBody HotelRoomsPK hotelRoomPK) {
        try {
            Bar bar = barService.findBarById(hotelRoomPK);
            barService.delete(bar);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while deleting bar info", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
