package com.teentech.hotels.service;

import com.teentech.hotels.model.MenuItem;
import com.teentech.hotels.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<MenuItem> getAllItemsFromHotel(int hotelId) { return menuRepository.findByHotelId(hotelId); }

    public void add(MenuItem menuItem) { menuRepository.save(menuItem); }

    public void delete(MenuItem menuItem) { menuRepository.delete(menuItem); }

    public MenuItem getItem(int id) {
        Optional<MenuItem> menuItem = menuRepository.findById(id);
        return menuItem.orElse(null);
    }
}
