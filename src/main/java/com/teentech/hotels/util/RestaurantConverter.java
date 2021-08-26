package com.teentech.hotels.util;

import com.teentech.hotels.dto.RestaurantDto;
import com.teentech.hotels.model.Restaurant;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RestaurantConverter {

    public static Restaurant convertDtoToEntity(RestaurantDto restaurantDto) {

        Restaurant restaurant = new Restaurant();

        restaurant.setReservationId(restaurantDto.getReservationId());
        restaurant.setLastBreakfastDate(restaurantDto.getLastBreakfastDate());
        restaurant.setLastLunchDate(restaurantDto.getLastLunchDate());
        restaurant.setLastDinnerDate(restaurantDto.getLastDinnerDate());

        return  restaurant;
    }

    public static RestaurantDto convertFromEntityToDto(Restaurant restaurant) {

        RestaurantDto restaurantDto = new RestaurantDto();

        restaurantDto.setReservationId(restaurant.getReservationId());
        restaurantDto.setLastBreakfastDate(restaurant.getLastBreakfastDate());
        restaurantDto.setLastLunchDate(restaurant.getLastLunchDate());
        restaurantDto.setLastDinnerDate(restaurant.getLastDinnerDate());

        return  restaurantDto;
    }
}
