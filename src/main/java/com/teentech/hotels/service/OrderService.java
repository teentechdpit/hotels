package com.teentech.hotels.service;

import com.teentech.hotels.model.Order;
import com.teentech.hotels.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Optional<Order> getOrderById(int id) { return orderRepository.findById(id); }

    public List<Order> getOrderByReservation(Long reservationId) { return orderRepository.findByReservationId(reservationId); }

    public void add(Order order) { orderRepository.save(order); }

    public void delete(Order order) { orderRepository.delete(order); }
}
