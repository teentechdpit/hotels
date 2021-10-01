package com.teentech.hotels.controller;

import com.teentech.hotels.dto.CleanDto;
import com.teentech.hotels.model.Clean;
import com.teentech.hotels.model.Hotel;
import com.teentech.hotels.model.HotelRoomsPK;
import com.teentech.hotels.service.CleanService;
import com.teentech.hotels.service.HotelService;
import com.teentech.hotels.util.CleanConverter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/cleaning")
@Log4j2
@SecurityRequirement(name = "bearerAuth")
public class CleanController {

    @Autowired
    private CleanService cleanService;

    @PreAuthorize("hasRole('ROLE_CLEAN PERSONAL') or hasRole('ROLE_MANAGER')")
    @GetMapping
    public ResponseEntity<CleanDto> getCleanById(@RequestParam long hotelId, @RequestParam long roomNumber ) {
        try {
            HotelRoomsPK hotelRoomsPK = HotelRoomsPK.builder().hotelId(hotelId).roomNumber(roomNumber).build();
            Clean clean = cleanService.findCleanById(hotelRoomsPK);
            if (clean == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            CleanDto cleanDto = CleanConverter.convertFromEntityToDto(clean);
            return new ResponseEntity<>(cleanDto, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while getting cleaning info", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ROLE_CLEAN PERSONAL') or hasRole('ROLE_MANAGER')")
    @PostMapping
    public ResponseEntity<Boolean> addClean(@RequestBody CleanDto cleanDto) {
        try {
            Clean clean = CleanConverter.convertFromDtoToEntity(cleanDto);

            cleanService.add(clean);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while adding new cleaning info into database", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ROLE_CLEAN PERSONAL') or hasRole('ROLE_MANAGER')")
    @PutMapping
    public ResponseEntity<Boolean> updateClean(@RequestBody CleanDto cleanDto) {
        try {
            Clean clean = CleanConverter.convertFromDtoToEntity(cleanDto);

            cleanService.update(clean);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while updating cleaning info", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PreAuthorize("hasRole('ROLE_CLEAN PERSONAL') or hasRole('ROLE_MANAGER')")
    @DeleteMapping
    public ResponseEntity<Boolean> deleteClean(@RequestBody HotelRoomsPK hotelRoomPK) {
        try {
            Clean clean = cleanService.findCleanById(hotelRoomPK);
            cleanService.delete(clean);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while deleting cleaning info", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
