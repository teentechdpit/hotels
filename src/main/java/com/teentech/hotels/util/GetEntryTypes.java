package com.teentech.hotels.util;

import com.teentech.hotels.dto.ItemTypeDto;
import com.teentech.hotels.dto.MenuItemForPageDto;
import com.teentech.hotels.model.ItemType;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class GetEntryTypes {

    public static List<String> getEntryTypes(List<MenuItemForPageDto> menu) {
        List<String> itemTypes = new ArrayList<>();
        for (MenuItemForPageDto menuItem : menu) {
            if (!itemTypes.contains(menuItem.getEntryType())) {
                itemTypes.add(menuItem.getEntryType());
            }
        }
        return itemTypes;
    }
}
