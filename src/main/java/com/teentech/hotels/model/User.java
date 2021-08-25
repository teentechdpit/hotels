package com.teentech.hotels.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    private String username;

    private String password;

    private String language;

    private String mail;

    @OneToOne
    private UserRole roles;

    @Column(name = "hotel_id")
    private long hotelId;
}

