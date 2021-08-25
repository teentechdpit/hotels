package com.teentech.hotels.util;

import com.teentech.hotels.dto.HotelDto;
import com.teentech.hotels.model.Hotel;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HotelConverter {

    public static Hotel convertFromDtoToEntity(HotelDto hotelDto) {

        Hotel hotel = new Hotel();

        hotel.setId(hotelDto.getId());
        hotel.setName(hotelDto.getName());
        hotel.setCountry(hotelDto.getCountry());
        hotel.setCity(hotelDto.getCity());
        hotel.setStars(hotelDto.getStars());
        hotel.setMail(hotelDto.getMail());
        hotel.setPhone(hotelDto.getPhone());
        hotel.setPaid(hotelDto.isPaid());

        return hotel;
    }

    public static HotelDto convertFromEntityToDto(Hotel hotel) {

        HotelDto hotelDto = new HotelDto();

        hotelDto.setId(hotel.getId());
        hotelDto.setName(hotel.getName());
        hotelDto.setCountry(hotel.getCountry());
        hotelDto.setCity(hotel.getCity());
        hotelDto.setStars(hotel.getStars());
        hotelDto.setMail(hotel.getMail());
        hotelDto.setPhone(hotel.getPhone());
        hotelDto.setPaid(hotel.isPaid());

        return hotelDto;
    }
}
