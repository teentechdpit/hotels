package com.teentech.hotels.controller;

import com.teentech.hotels.dto.HotelDto;
import com.teentech.hotels.model.Hotel;
import com.teentech.hotels.service.HotelService;
import com.teentech.hotels.util.HotelConverter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/hotels")
@Log4j2
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        try {
            log.info("Get all hotels called");
            return new ResponseEntity<>(hotelService.getAllHotels(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while getting all hotels", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        try {
            log.info("Get hotel with id {}", id);
            Optional<Hotel> hotel = hotelService.getHotelById(id);
            if (hotel.isPresent()){
                return new ResponseEntity<>(hotel.get(), HttpStatus.OK);
            } else {
                log.error("Nothing found");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            log.error("Error while getting a hotel", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Boolean> addHotel(@RequestBody HotelDto hotelDto) {
        try {
            Hotel hotel = HotelConverter.convertFromDtoToEntity(hotelDto);
            hotelService.add(hotel);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while adding a new hotel into database", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Boolean> updateHotel(@RequestBody HotelDto hotelDto) {
        try {
            Hotel hotel = HotelConverter.convertFromDtoToEntity(hotelDto);
            hotelService.update(hotel);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while updating a hotel", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> updateHotel(@PathVariable Long id) {
        try {
            Optional<Hotel> hotel = hotelService.getHotelById(id);
            if (hotel.isPresent()) {
                hotelService.delete(hotel.get());
            }
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while deleting a hotel", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
