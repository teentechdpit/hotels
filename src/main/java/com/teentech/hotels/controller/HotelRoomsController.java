package com.teentech.hotels.controller;

import com.teentech.hotels.model.HotelRooms;
import com.teentech.hotels.model.HotelRoomsPK;
import com.teentech.hotels.service.HotelRoomsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/rooms")
@Log4j2
public class HotelRoomsController {

    @Autowired
    private HotelRoomsService hotelRoomsService;

    @PostMapping
    public ResponseEntity addRoom(@RequestBody HotelRooms hotelRoom) {
        try {
            hotelRoomsService.add(hotelRoom);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while adding a new room into database", e);
            return new ResponseEntity("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity deleteRoom(@RequestBody HotelRoomsPK hotelRoomPK) {
        try {
            HotelRooms room = hotelRoomsService.findHotelRoomById(hotelRoomPK);
            hotelRoomsService.delete(room);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while deleting a  room from the database", e);
            return new ResponseEntity("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

