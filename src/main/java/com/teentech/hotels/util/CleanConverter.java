package com.teentech.hotels.util;

import com.teentech.hotels.dto.CleanDto;
import com.teentech.hotels.model.Clean;

public class CleanConverter {

    public static Clean convertFromDtoToEntity(CleanDto cleanDto) {

        Clean clean = new Clean();

        clean.setHotelId(cleanDto.getHotelId());
        clean.setRoomNumber(cleanDto.getRoomNumber());

        clean.setLastCleanDay(cleanDto.getLastCleanDay());
        clean.setLastChangeLingerie(cleanDto.getLastChangeLingerie());
        clean.setLastChangeTowels(cleanDto.getLastChangeTowels());

        return clean;
    }

    public static CleanDto convertFromEntityToDto(Clean clean) {

        CleanDto cleanDto = new CleanDto();

        cleanDto.setHotelId(clean.getHotelId());
        cleanDto.setRoomNumber(clean.getRoomNumber());

        cleanDto.setLastCleanDay(clean.getLastCleanDay());
        cleanDto.setLastChangeLingerie(clean.getLastChangeLingerie());
        cleanDto.setLastChangeTowels(clean.getLastChangeTowels());

        return cleanDto;
    }
}
