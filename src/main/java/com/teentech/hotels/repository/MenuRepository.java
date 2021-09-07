package com.teentech.hotels.repository;

import com.teentech.hotels.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<MenuItem, Integer> {

    List<MenuItem> findByHotelId(int hotelId);
}
