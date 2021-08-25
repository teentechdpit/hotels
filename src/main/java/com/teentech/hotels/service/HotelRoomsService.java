package com.teentech.hotels.service;


import com.teentech.hotels.model.HotelRooms;
import com.teentech.hotels.model.HotelRoomsPK;
import com.teentech.hotels.repository.HotelRoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HotelRoomsService {

    @Autowired
    private HotelRoomsRepository hotelRoomsRepository;

    public void add(HotelRooms hotelRoom) {
        hotelRoomsRepository.save(hotelRoom);
    }

    public void delete(HotelRooms hotelRoom) {
        hotelRoomsRepository.delete(hotelRoom);
    }

    public HotelRooms findHotelRoomById(HotelRoomsPK hotelRoomPK) {
        Optional<HotelRooms> currentHotelRoom = hotelRoomsRepository.findById(hotelRoomPK);
        return currentHotelRoom.orElse(null);
    }

    public List<HotelRooms> findAvailableRoom(Long hotelId, String View, Date startDate, Date endDate, int noOfPeople) {
        List<HotelRooms> foundRooms = hotelRoomsRepository.findAvailableHotelRooms(hotelId, View, startDate, endDate, noOfPeople);
        return foundRooms;
    }

}
