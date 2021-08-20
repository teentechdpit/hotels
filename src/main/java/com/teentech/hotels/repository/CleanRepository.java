package com.teentech.hotels.repository;

import com.teentech.hotels.model.Clean;
import com.teentech.hotels.model.HotelRoomsPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CleanRepository extends JpaRepository<Clean, HotelRoomsPK> {

}
