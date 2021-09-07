package com.teentech.hotels.service;

import com.teentech.hotels.dto.MenuItemDto;
import com.teentech.hotels.dto.MenuItemForPageDto;
import com.teentech.hotels.model.ItemType;
import com.teentech.hotels.model.MenuItem;
import com.teentech.hotels.repository.ItemTypeRepository;
import com.teentech.hotels.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MenuItemForPageService {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ItemTypeRepository itemTypeRepository;

    public List<MenuItemForPageDto> getAllFromHotel(int hotelId) {
        List<MenuItem> menuItems = menuRepository.findByHotelId(hotelId);
        List<MenuItemForPageDto> menuItemsForPageDto= new ArrayList<>();
        for (MenuItem menuItem : menuItems) {
            ItemType itemType = itemTypeRepository.findById(menuItem.getEntryTypeId()).orElse(null);
            MenuItemForPageDto menuItemForPageDto = MenuItemForPageDto.builder().hotelId(menuItem.getHotelId()).nameOfEntry(menuItem.getNameOfEntry()).entryType(itemType.getName()).price(menuItem.getPrice()).currency(menuItem.getCurrency()).build();
            menuItemsForPageDto.add(menuItemForPageDto);
        }
        return menuItemsForPageDto;
    }
}
