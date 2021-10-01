package com.teentech.hotels.controller;

import com.teentech.hotels.dto.EmailDto;
import com.teentech.hotels.dto.OrderDto;
import com.teentech.hotels.model.Order;
import com.teentech.hotels.model.Reservations;
import com.teentech.hotels.service.MailService;
import com.teentech.hotels.service.OrderService;
import com.teentech.hotels.service.ReservationService;
import com.teentech.hotels.util.OrderConverter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/order")
@Log4j2
@SecurityRequirement(name = "bearerAuth")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private MailService mailService;

    @PreAuthorize("hasRole('ROLE_RESTAURANT MEMBER') or hasRole('ROLE_MANAGER')")
    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrderOfReservation(@RequestParam Long reservationID) {
        try {
            List<Order> orders = orderService.getOrderByReservation(reservationID);

            if(orders.isEmpty()) {
                log.error("There is no order for this reservation id");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            List<OrderDto> ordersDto = new ArrayList<>();

            for(Order order : orders) {
                ordersDto.add(OrderConverter.convertFromEntityToDto(order));
            }

            return new ResponseEntity<>(ordersDto, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while getting orders for this reservation from the DB", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ROLE_RESTAURANT MEMBER') or hasRole('ROLE_MANAGER')")
    @PostMapping
    public ResponseEntity<Boolean> addOrder(@RequestBody List<OrderDto> ordersDto) {
        try {
            Reservations reservation = reservationService.getReservationById(ordersDto.get(0).getReservationId());
            if (reservation == null)
            {
                log.error("No reservation with this id");
                return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NO_CONTENT);
            }

            Timestamp currentDate = new Timestamp(System.currentTimeMillis());

            for (int i = 0; i < ordersDto.size(); i++) {
                ordersDto.get(i).setOrderDate(currentDate);
            }

            StringBuilder bld = new StringBuilder();

            bld.append("<h1 style=\"text-align:center;  font-family:'FranklinGothicMedium','ArialNarrow',Arial,sans-serif;" +
                    "font-weight:100;font-size:2vw;color:#0e77de;margin-top:3vw;margin-bottom:3vw;\">&#127789; Order confirmation &#127790;</h1>" +
                    "<p style=\"text-align:center;font-family:'GillSans','GillSansMT',Calibri,'TrebuchetMS',sans-serif;font-size:1vw;\">");

            Long totalPrice = 0L;
            for (OrderDto orderDto : ordersDto) {
                orderService.add(OrderConverter.convertFromDtoToEntity(orderDto));
                totalPrice += orderDto.getTotalPrice();
                bld.append("You have ordered <b>").append(orderDto.getNumberOfPieces()).append(" pieces of ").append(orderDto.getNameOfEntry()).append(" with price of ").append(orderDto.getTotalPrice()).append(" ").append(orderDto.getCurrency()).append(" &#129316; </b>.<br>");
            }

            reservation.setCheckout(reservation.getCheckout() + totalPrice);

            reservation.setCurrency(ordersDto.get(0).getCurrency());

            reservationService.update(reservation);

            bld.append("The total price of the order is <b>").append(totalPrice).append(" ").append(ordersDto.get(0).getCurrency()).append("</b>. Thanks for ordering from us!</p>");

            String htmlText = bld.toString();

            EmailDto emailDto = EmailDto.builder().to(reservation.getEmail()).subject("Order confirmation").content(htmlText).build();

            mailService.send(emailDto);

            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while adding order to DB", e);
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
