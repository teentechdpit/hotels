package com.teentech.hotels.service;


import com.teentech.hotels.model.Clean;
import com.teentech.hotels.model.HotelRoomsPK;
import com.teentech.hotels.repository.CleanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CleanService {

    @Autowired
    private CleanRepository cleanRepository;

    public void add(Clean clean) { cleanRepository.save(clean); }

    public void update(Clean clean) {
        cleanRepository.save(clean);
    }

    public void delete(Clean clean) {
        cleanRepository.delete(clean);
    }

    public Clean findCleanById(HotelRoomsPK hotelRoomPK) {
        Optional<Clean> currentClean = cleanRepository.findById(hotelRoomPK);
        return currentClean.orElse(null);
    }


}
