package com.teentech.hotels.util;

import com.teentech.hotels.dto.SimpleUserDto;
import com.teentech.hotels.dto.UserDto;
import com.teentech.hotels.model.User;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class UserConverter {

    public static User convertFromDtoToEntity(UserDto userDto) {

        User user = new User();

        user.setUsername(userDto.getUsername());
        user.setLanguage(userDto.getLanguage());
        user.setMail(userDto.getMail());
        user.setRoleId(userDto.getRoleId());
        user.setHotelId(userDto.getHotelId());

        return user;
    }

    public static UserDto convertFromEntityToDto(User user) {

        UserDto userDto = new UserDto();

        userDto.setUsername(user.getUsername());
        userDto.setLanguage(user.getLanguage());
        userDto.setMail(user.getMail());
        userDto.setHotelId(user.getHotelId());

        userDto.setRoleId(user.getRole().getId());

        List<String> rights = new ArrayList<>();
        user.getRole().getRights().stream().forEach(r -> rights.add(r.getName()));
        userDto.setRights(rights);

        return userDto;
    }

    public static User convertFromSimpleDtoToEntity(SimpleUserDto simpleUserDto) {
        User user = new User();

        user.setUsername(simpleUserDto.getUsername());
        user.setLanguage(simpleUserDto.getLanguage());
        user.setMail(simpleUserDto.getMail());
        user.setRoleId(simpleUserDto.getRoleId());
        user.setHotelId(simpleUserDto.getHotelId());

        return user;
    }

}
