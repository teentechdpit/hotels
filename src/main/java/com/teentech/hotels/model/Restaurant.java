package com.teentech.hotels.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Restaurant implements Serializable {

    @Id
    @Column(name = "reservation_id")
    private Long reservationId;

    @Column(name = "last_breakfast_date")
    private Date lastBreakfastDate;

    @Column(name = "last_lunch_date")
    private Date lastLunchDate;

    @Column(name = "last_dinner_date")
    private Date lastDinnerDate;
}
