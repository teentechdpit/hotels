package com.teentech.hotels.util;

import com.teentech.hotels.dto.OrderDto;
import com.teentech.hotels.model.Order;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderConverter {

    public static Order convertFromDtoToEntity(OrderDto orderDto) {

        Order order = new Order();

        order.setReservationId(orderDto.getReservationId());

        order.setNameOfEntry(orderDto.getNameOfEntry());

        order.setNumberOfPieces(orderDto.getNumberOfPieces());

        order.setOrderDate(orderDto.getOrderDate());

        order.setTotalPrice(orderDto.getTotalPrice());

        order.setCurrency(orderDto.getCurrency());

        return order;
    }

    public static OrderDto convertFromEntityToDto(Order order) {

        OrderDto orderDto = new OrderDto();

        orderDto.setReservationId(order.getReservationId());

        orderDto.setNameOfEntry(order.getNameOfEntry());

        orderDto.setNumberOfPieces(order.getNumberOfPieces());

        orderDto.setOrderDate(order.getOrderDate());

        orderDto.setTotalPrice(order.getTotalPrice());

        orderDto.setCurrency(order.getCurrency());

        return orderDto;
    }
}
