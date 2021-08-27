package com.teentech.hotels.controller;

import com.teentech.hotels.dto.BarDto;
import com.teentech.hotels.dto.CleanDto;
import com.teentech.hotels.model.Bar;
import com.teentech.hotels.model.Clean;
import com.teentech.hotels.model.HotelRoomsPK;
import com.teentech.hotels.service.BarService;
import com.teentech.hotels.service.CleanService;
import com.teentech.hotels.util.BarConverter;
import com.teentech.hotels.util.CleanConverter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/bar")
@Log4j2
public class BarController {

    @Autowired
    private BarService barService;

    @GetMapping
    public ResponseEntity<BarDto> getBarById(@RequestParam long hotelId, @RequestParam long roomNumber) {
        try {
            HotelRoomsPK hotelRoomsPK = HotelRoomsPK.builder().hotelId(hotelId).roomNumber(roomNumber).build();
            Bar bar = barService.findBarById(hotelRoomsPK);
            BarDto barDto = BarConverter.convertFromEntityToDto(bar);
            return new ResponseEntity<>(barDto, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while getting bar info", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
