package com.teentech.hotels.service;


import com.teentech.hotels.model.Hotel;
import com.teentech.hotels.model.HotelRooms;
import com.teentech.hotels.model.HotelRoomsPK;
import com.teentech.hotels.repository.HotelRepository;
import com.teentech.hotels.repository.HotelRoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelRoomsService {

    @Autowired
    private HotelRoomsRepository hotelRoomsRepository;

//    public List<Hotel> getAllHotels() {
//        return hotelRepository.findAll();
//    }
//
//    public Optional<Hotel> getHotelById(Long id) {
//        return hotelRepository.findById(id);
//    }

    public void add(HotelRooms hotelRoom) {
        hotelRoomsRepository.save(hotelRoom);
    }


//    public void update(Hotel hotel) {
//        hotelRepository.save(hotel);
//    }
//
    public void delete(HotelRooms hotelRoom) {
        hotelRoomsRepository.delete(hotelRoom);
    }

    public HotelRooms findHotelRoomById(HotelRoomsPK hotelRoomPK) {
        Optional<HotelRooms> currentHotelRoom = hotelRoomsRepository.findById(hotelRoomPK);
        return currentHotelRoom.orElse(null);
    }


}
