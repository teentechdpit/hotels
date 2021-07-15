package com.teentech.hotels.service;


import com.teentech.hotels.model.Hotel;
import com.teentech.hotels.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Optional<Hotel> getHotelById(Long id) {
        return hotelRepository.findById(id);
    }

    public void add(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    public void update(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    public void delete(Hotel hotel) {
        hotelRepository.delete(hotel);
    }
}
