package com.teentech.hotels.repository;

import com.teentech.hotels.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository  extends JpaRepository<Hotel, Long> {

    List<Hotel> findByName(String name);
}
