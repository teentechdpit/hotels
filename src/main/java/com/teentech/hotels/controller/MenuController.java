package com.teentech.hotels.controller;

import com.teentech.hotels.dto.MenuItemDto;
import com.teentech.hotels.model.MenuItem;
import com.teentech.hotels.service.ItemTypeService;
import com.teentech.hotels.service.MenuService;
import com.teentech.hotels.util.MenuItemConverter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/menu")
@Log4j2
@SecurityRequirement(name = "bearerAuth")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private ItemTypeService itemTypeService;

    @GetMapping("{hotelId}")
    public ResponseEntity<List<MenuItemDto>> getMenuByHotelId(@PathVariable int hotelId) {
        try {
            List<MenuItem> menu = menuService.getAllItemsFromHotel(hotelId);
            List<MenuItemDto> menuDto = new ArrayList<>();

            for (MenuItem menuItem : menu)
            {
                menuDto.add(MenuItemConverter.convertFromEntityToDto(menuItem));
            }

            return new ResponseEntity<>(menuDto,HttpStatus.OK);
        } catch (Exception e) {
            log.error("Could not get the menu of this hotel", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Boolean> addItemToMenu(@RequestBody MenuItemDto menuItemDto) {
        try {
            MenuItem menuItem = MenuItemConverter.convertFromDtoToEntity(menuItemDto);

            menuService.add(menuItem);

            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while adding the item to the DB", e);
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.INTERNAL_SERVER_ERROR);        }
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteItemFromMenu(@RequestParam int id) {
        try {
            MenuItem menuItem = menuService.getItem(id);

            if (menuItem == null) {
                log.error("Item does not exist in DB");
                return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NO_CONTENT);
            }

            menuService.delete(menuItem);

            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while deleting the item from the DB", e);
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
