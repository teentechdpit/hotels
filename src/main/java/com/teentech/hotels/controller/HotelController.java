package com.teentech.hotels.controller;

import com.teentech.hotels.dto.HotelDto;
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
                return new ResponseEntity(hotel.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity("Nothing found",HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity addHotel(@RequestBody HotelDto hotelDto) {
        try {
            Hotel hotel = new Hotel();
            hotel.setId(hotelDto.getId());
            hotel.setName(hotelDto.getName());
            hotel.setCountry(hotelDto.getCountry());
            hotel.setCity(hotelDto.getCity());
            hotel.setStars(hotelDto.getStars());
            hotel.setMail(hotelDto.getMail());
            hotel.setPhone(hotelDto.getPhone());
            hotel.setPaid(hotelDto.isPaid());
            hotelService.add(hotel);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while adding a new hotel into database", e);
            return new ResponseEntity("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity updateHotel(@RequestBody HotelDto hotelDto) {
        try {
            Hotel hotel = new Hotel();
            hotel.setId(hotelDto.getId());
            hotel.setName(hotelDto.getName());
            hotel.setCountry(hotelDto.getCountry());
            hotel.setCity(hotelDto.getCity());
            hotel.setStars(hotelDto.getStars());
            hotel.setMail(hotelDto.getMail());
            hotel.setPhone(hotelDto.getPhone());
            hotel.setPaid(hotelDto.isPaid());
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
