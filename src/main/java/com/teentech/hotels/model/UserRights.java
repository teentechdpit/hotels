package com.teentech.hotels.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users_Rights")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRights implements Serializable{
    @Id
    String name;

    String description;

}
