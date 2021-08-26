package com.teentech.hotels.service;


import com.teentech.hotels.model.Restaurant;
import com.teentech.hotels.repository.ReservationsRepository;
import com.teentech.hotels.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ReservationsRepository reservationsRepository;

    public void add(Restaurant restaurant ) { restaurantRepository.save(restaurant); };

    public void update(Restaurant restaurant) { restaurantRepository.save(restaurant); };

    public Restaurant findRestaurantByReservationId(int reservationId) {
        Optional<Restaurant> currentRestaurant = restaurantRepository.findById(reservationId);
        return currentRestaurant.orElse(null);
    }
}
