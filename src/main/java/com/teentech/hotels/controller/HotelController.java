package com.teentech.hotels.controller;

import com.teentech.hotels.model.Hotel;
import com.teentech.hotels.service.HotelService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/hotels")
@Log4j2
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public ResponseEntity getAllHotels() {
        try {
            log.info("Get all hotels called");
            return new ResponseEntity(hotelService.getAllHotels(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity getHotelById(@PathVariable Long id) {
        try {
            log.info("Get hotel with id {}", id);
            Optional<Hotel> hotel = hotelService.getHotelById(id);
            if (hotel.isPresent()){
                return new ResponseEntity(hotel, HttpStatus.OK);
            } else {
                return new ResponseEntity("Nothing found",HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity addHotel(@RequestBody Hotel hotel) {
        try {
            hotelService.add(hotel);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity updateHotel(@RequestBody Hotel hotel) {
        try {
            hotelService.update(hotel);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity updateHotel(@PathVariable Long id) {
        try {
            Optional<Hotel> hotel = hotelService.getHotelById(id);
            if (hotel.isPresent()) {
                hotelService.delete(hotel.get());
            }
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
