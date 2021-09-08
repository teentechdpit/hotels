package com.teentech.hotels.controller;

import com.teentech.hotels.dto.ItemTypeDto;
import com.teentech.hotels.model.ItemType;
import com.teentech.hotels.service.ItemTypeService;
import com.teentech.hotels.util.ItemTypeConverter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/menu/type")
@Log4j2
@SecurityRequirement(name = "bearerAuth")
public class ItemTypeController {

    @Autowired
    private ItemTypeService itemTypeService;

    @PostMapping
    public ResponseEntity<Boolean> addItemType(@RequestBody ItemTypeDto itemTypeDto) {
        try {
            ItemType itemType = ItemTypeConverter.convertFromDtoToEntity(itemTypeDto);

            itemTypeService.add(itemType);

            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while adding item type to DB", e);
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
