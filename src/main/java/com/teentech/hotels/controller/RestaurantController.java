package com.teentech.hotels.controller;

import com.teentech.hotels.dto.ReservationDto;
import com.teentech.hotels.dto.RestaurantDto;
import com.teentech.hotels.model.Reservations;
import com.teentech.hotels.model.Restaurant;
import com.teentech.hotels.service.ReservationService;
import com.teentech.hotels.service.RestaurantService;
import com.teentech.hotels.util.RestaurantConverter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/restaurant")
@Log4j2
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public ResponseEntity<RestaurantDto> getRestaurantByHotelRoomPk(@RequestParam long hotelId, @RequestParam long roomNumber) {
        try {
            Reservations reservation = reservationService.getCurrentReservation(hotelId, roomNumber);
            if(reservation == null) {
                log.error("There is no reservation active");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            Long reservationId = reservation.getId();
            Restaurant currentRestaurant = restaurantService.findRestaurantByReservationId(reservationId);
            RestaurantDto currentRestaurantDto = RestaurantConverter.convertFromEntityToDto(currentRestaurant);
            return new ResponseEntity<>(currentRestaurantDto, HttpStatus.OK);
        } catch(Exception e) {
            log.error("Error while getting restaurant info", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Boolean> updateRestaurant(@RequestBody RestaurantDto currentRestaurantDto) {
        try {
            Reservations reservation = reservationService.getReservationById(currentRestaurantDto.getReservationId());
            if(reservation == null) {
                log.error("There is no reservation active");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            Restaurant currentRestaurant = RestaurantConverter.convertDtoToEntity(currentRestaurantDto);

            restaurantService.update(currentRestaurant);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch(Exception e) {
            log.error("Error while updating restaurant", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
