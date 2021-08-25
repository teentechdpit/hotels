package com.teentech.hotels.controller;

import com.teentech.hotels.dto.CleanDto;
import com.teentech.hotels.model.Clean;
import com.teentech.hotels.model.Hotel;
import com.teentech.hotels.model.HotelRoomsPK;
import com.teentech.hotels.service.CleanService;
import com.teentech.hotels.service.HotelService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/cleaning")
@Log4j2
public class CleanController {

    @Autowired
    private CleanService cleanService;

    @GetMapping
    public ResponseEntity<CleanDto> getCleanById(@RequestParam long hotelId, @RequestParam long roomNumber ) {
        try {
            HotelRoomsPK hotelRoomsPK = HotelRoomsPK.builder().hotelId(hotelId).roomNumber(roomNumber).build();
            Clean clean = cleanService.findCleanById(hotelRoomsPK);
            CleanDto cleanDto = CleanDto.builder().hotelId(clean.getHotelId()).roomNumber(clean.getRoomNumber()).lastCleanDay(clean.getLastCleanDay()).lastChangeLingerie(clean.getLastChangeLingerie()).lastChangeTowels(clean.getLastChangeTowels()).build();
            return new ResponseEntity<CleanDto>(cleanDto, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while getting cleaning info", e);
            return new ResponseEntity("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity addClean(@RequestBody CleanDto cleanDto) {
        try {
            Clean clean = new Clean();
            clean.setHotelId(cleanDto.getHotelId());
            clean.setRoomNumber(cleanDto.getRoomNumber());
            clean.setLastCleanDay(cleanDto.getLastCleanDay());
            clean.setLastChangeLingerie(cleanDto.getLastChangeLingerie());
            clean.setLastChangeTowels(cleanDto.getLastChangeTowels());
            cleanService.add(clean);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while adding new cleaning info into database", e);
            return new ResponseEntity("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity updateClean(@RequestBody CleanDto cleanDto) {
        try {
            Clean clean = new Clean();
            clean.setHotelId(cleanDto.getHotelId());
            clean.setRoomNumber(cleanDto.getRoomNumber());
            clean.setLastCleanDay(cleanDto.getLastCleanDay());
            clean.setLastChangeLingerie(cleanDto.getLastChangeLingerie());
            clean.setLastChangeTowels(cleanDto.getLastChangeTowels());
            cleanService.update(clean);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while updating cleaning info", e);
            return new ResponseEntity("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping
    public ResponseEntity deleteClean(@RequestBody HotelRoomsPK hotelRoomPK) {
        try {
            Clean clean = cleanService.findCleanById(hotelRoomPK);
            cleanService.delete(clean);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while deleting cleaning info", e);
            return new ResponseEntity("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
