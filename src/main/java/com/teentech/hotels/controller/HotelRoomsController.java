package com.teentech.hotels.controller;

import com.teentech.hotels.dto.HotelRoomsDto;
import com.teentech.hotels.model.HotelRooms;
import com.teentech.hotels.model.HotelRoomsPK;
import com.teentech.hotels.repository.HotelRoomsRepository;
import com.teentech.hotels.service.HotelRoomsService;
import com.teentech.hotels.util.HotelRoomsConverter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/rooms")
@Log4j2
public class HotelRoomsController {

    @Autowired
    private HotelRoomsService hotelRoomsService;

    @PostMapping
    public ResponseEntity addRoom(@RequestBody HotelRoomsDto hotelRoomDto) {
        try {
            HotelRooms hotelRoom = HotelRoomsConverter.convertFromDtoToEntity(hotelRoomDto);
            hotelRoomsService.add(hotelRoom);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while adding a new room into database", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<HotelRoomsDto>> findRooms(@RequestParam Long hotelId, @RequestParam String view, @RequestParam java.sql.Date startDate, @RequestParam Date endDate, @RequestParam int noOfPeople, @RequestParam String roomType) {
        try {
            List<HotelRooms> availableRooms = hotelRoomsService.findAvailableRoom(hotelId, view, startDate, endDate, noOfPeople, roomType);
            if (availableRooms.isEmpty()) {
                return new ResponseEntity("No available room found", HttpStatus.NO_CONTENT);
            }
            List<HotelRoomsDto> availableRoomsDto = new ArrayList<>();
            for (HotelRooms availableRoom : availableRooms) {
                HotelRoomsDto availableRoomDto = HotelRoomsDto.builder().roomNumber(availableRoom.getRoomNumber()).hotelId(availableRoom.getHotelId()).type(availableRoom.getType()).roomView(availableRoom.getRoomView()).noOfPeople(availableRoom.getNoOfPeople()).build();
                availableRoomsDto.add(availableRoomDto);
            }
            return new ResponseEntity<List<HotelRoomsDto>>(availableRoomsDto, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while getting available rooms", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity deleteRoom(@RequestBody HotelRoomsPK hotelRoomPK) {
        try {
            HotelRooms room = hotelRoomsService.findHotelRoomById(hotelRoomPK);
            hotelRoomsService.delete(room);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while deleting a room from the database", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

