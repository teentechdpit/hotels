package com.teentech.hotels.service;

import com.teentech.hotels.dto.RoleDto;
import com.teentech.hotels.model.UserRole;
import com.teentech.hotels.repository.UserRoleRepository;
import com.teentech.hotels.util.UserRoleConverter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    public List<RoleDto> getAllRoles() {
        List<UserRole> roleList = userRoleRepository.findAll();
        List<RoleDto> roleDtoList = new ArrayList<>();
        for (UserRole currentRole : roleList) {
            RoleDto currentRoleDto = UserRoleConverter.convertFromEntityToDto(currentRole);
            roleDtoList.add(currentRoleDto);
        }
        return roleDtoList;
    }

}
