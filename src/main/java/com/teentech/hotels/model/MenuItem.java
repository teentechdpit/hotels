package com.teentech.hotels.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "menu")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "hotel_id")
    private int hotelId;

    @Column(name = "name_of_entry")
    private String nameOfEntry;

    @Column(name = "entry_type_id")
    private int entryTypeId;

    @Column(name = "price")
    private int price;

    @Column(name = "currency")
    private String currency;
}
