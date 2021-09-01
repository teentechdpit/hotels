package com.teentech.hotels.service;


import com.teentech.hotels.model.Bar;
import com.teentech.hotels.model.HotelRoomsPK;
import com.teentech.hotels.repository.BarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BarService {

    @Autowired
    private BarRepository barRepository;

    public void add(Bar bar) { barRepository.save(bar); }

    public void update(Bar bar) {
        barRepository.save(bar);
    }

    public void delete(Bar bar) {
        barRepository.delete(bar);
    }

    public Bar findBarById(HotelRoomsPK hotelRoomPK) {
        Optional<Bar> currentBar = barRepository.findById(hotelRoomPK);
        return currentBar.orElse(null);
    }


}
