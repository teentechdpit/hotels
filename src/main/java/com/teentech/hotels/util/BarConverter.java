package com.teentech.hotels.util;

import com.teentech.hotels.dto.BarDto;
import com.teentech.hotels.dto.CleanDto;
import com.teentech.hotels.model.Bar;
import com.teentech.hotels.model.Clean;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BarConverter {

    public static Bar convertFromDtoToEntity(BarDto barDto) {

        Bar bar = new Bar();

        bar.setHotelId(barDto.getHotelId());
        bar.setRoomNumber(barDto.getRoomNumber());

        bar.setNoOfDrinks(barDto.getNoOfDrinks());

        return bar;
    }

    public static BarDto convertFromEntityToDto(Bar bar) {

        BarDto barDto = new BarDto();

        barDto.setHotelId(bar.getHotelId());
        barDto.setRoomNumber(bar.getRoomNumber());

        barDto.setNoOfDrinks(bar.getNoOfDrinks());

        return barDto;
    }
}
