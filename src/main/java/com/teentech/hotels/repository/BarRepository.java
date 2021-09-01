package com.teentech.hotels.repository;

import com.teentech.hotels.model.Bar;
import com.teentech.hotels.model.HotelRoomsPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarRepository extends JpaRepository<Bar, HotelRoomsPK> {
}
