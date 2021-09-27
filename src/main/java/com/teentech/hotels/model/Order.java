package com.teentech.hotels.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    @Column(name = "reservation_id")
    private Long reservationId;

    @Column(name = "name_of_entry")
    private String nameOfEntry;

    @Column(name = "number_of_pieces")
    private int numberOfPieces;

    @Column(name = "order_date")
    private Timestamp orderDate;

    @Column(name = "total_price")
    private int totalPrice;

    @Column(name = "currency")
    private String currency;
}
