package com.teentech.hotels.util;

import com.teentech.hotels.dto.UserDto;
import com.teentech.hotels.model.User;
import com.teentech.hotels.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserConverter {

    @Autowired
    private static UserRoleRepository userRoleRepository;

    public static User convertFromDtoToEntity(UserDto userDto) {

        User user = new User();

        user.setUsername(userDto.getUsername());
        user.setLanguage(userDto.getLanguage());
        user.setMail(userDto.getMail());
        user.setRoles(userRoleRepository.findByName(userDto.getRoleName()));
        user.setHotelId(userDto.getHotelId());

        return user;
    }

    public static UserDto convertFromEntityToDto(User user) {

        UserDto userDto = new UserDto();

        userDto.setUsername(user.getUsername());
        userDto.setLanguage(user.getLanguage());
        userDto.setMail(user.getMail());
        userDto.setHotelId(user.getHotelId());

        userDto.setRoleName(user.getRoles().getName());

        List<String> rights = new ArrayList<>();
        user.getRoles().getRights().stream().forEach(r -> rights.add(r.getName()));
        userDto.setRights(rights);

        return userDto;
    }

}
