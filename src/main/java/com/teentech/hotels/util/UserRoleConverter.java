package com.teentech.hotels.util;

import com.teentech.hotels.dto.RoleDto;
import com.teentech.hotels.model.UserRole;

public class UserRoleConverter {

    public static RoleDto convertFromEntityToDto(UserRole userRole) {

        RoleDto roleDto = new RoleDto();

        roleDto.setId(userRole.getId());
        roleDto.setName(userRole.getName());
        roleDto.setDescription(userRole.getDescription());

        return roleDto;
    }
}
