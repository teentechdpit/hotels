package com.teentech.hotels.util;

import com.teentech.hotels.dto.ItemTypeDto;
import com.teentech.hotels.model.ItemType;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ItemTypeConverter {

    public static ItemType convertFromDtoToEntity(ItemTypeDto itemTypeDto){

        ItemType itemType = new ItemType();

        itemType.setName(itemTypeDto.getName());

        return itemType;
    }

    public static ItemTypeDto convertFromEntityToDto(ItemType itemType) {

        ItemTypeDto itemTypeDto = new ItemTypeDto();

        itemTypeDto.setName(itemType.getName());

        return itemTypeDto;
    }
}
