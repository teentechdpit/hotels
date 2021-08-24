package com.teentech.hotels.service;


import com.teentech.hotels.model.Clean;
import com.teentech.hotels.model.HotelRoomsPK;
import com.teentech.hotels.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

}
