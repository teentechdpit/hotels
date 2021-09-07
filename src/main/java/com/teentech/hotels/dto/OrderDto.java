package com.teentech.hotels.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto implements Serializable {

    private Long reservationId;

    private String nameOfEntry;

    private int numberOfPieces;

    private Timestamp orderDate;

    private int totalPrice;

    private String currency;
}
