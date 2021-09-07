package com.teentech.hotels.util;

import com.teentech.hotels.dto.MenuItemDto;
import com.teentech.hotels.model.MenuItem;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MenuItemConverter {

    public static MenuItem convertFromDtoToEntity(MenuItemDto menuItemDto) {

        MenuItem menuItem = new MenuItem();

        menuItem.setHotelId(menuItemDto.getHotelId());

        menuItem.setNameOfEntry(menuItemDto.getNameOfEntry());

        menuItem.setEntryTypeId(menuItemDto.getEntryTypeId());

        menuItem.setPrice(menuItemDto.getPrice());

        menuItem.setCurrency(menuItemDto.getCurrency());

        return menuItem;
    }

    public static MenuItemDto convertFromEntityToDto(MenuItem menuItem) {

        MenuItemDto menuItemDto = new MenuItemDto();

        menuItemDto.setHotelId(menuItem.getHotelId());

        menuItemDto.setNameOfEntry(menuItem.getNameOfEntry());

        menuItemDto.setEntryTypeId(menuItem.getEntryTypeId());

        menuItemDto.setPrice(menuItem.getPrice());

        menuItemDto.setCurrency(menuItem.getCurrency());

        return menuItemDto;
    }
}
