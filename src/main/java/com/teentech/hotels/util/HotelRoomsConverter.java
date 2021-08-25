package com.teentech.hotels.util;

import com.teentech.hotels.dto.HotelRoomsDto;
import com.teentech.hotels.model.HotelRooms;

public class HotelRoomsConverter {

    public static HotelRooms convertFromDtoToEntity(HotelRoomsDto hotelRoomDto) {

        HotelRooms hotelRoom = new HotelRooms();

        hotelRoom.setRoomNumber(hotelRoomDto.getRoomNumber());
        hotelRoom.setHotelId(hotelRoomDto.getHotelId());
        hotelRoom.setType(hotelRoomDto.getType());
        hotelRoom.setRoomView(hotelRoomDto.getRoomView());
        hotelRoom.setNoOfPeople(hotelRoomDto.getNoOfPeople());

        return hotelRoom;
    }

    public static HotelRoomsDto convertFromEntityToDto(HotelRooms hotelRoom) {

        HotelRoomsDto hotelRoomDto = new HotelRoomsDto();

        hotelRoomDto.setRoomNumber(hotelRoom.getRoomNumber());
        hotelRoomDto.setHotelId(hotelRoom.getHotelId());
        hotelRoomDto.setType(hotelRoom.getType());
        hotelRoomDto.setRoomView(hotelRoom.getRoomView());
        hotelRoomDto.setNoOfPeople(hotelRoom.getNoOfPeople());

        return hotelRoomDto;
    }
}
