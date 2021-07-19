package com.teentech.hotels.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users_Roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRoles implements Serializable{
    @Id
    String name;

    String description;

    String rightName;

    @OneToMany
    @JoinColumn(name = "rightName",referencedColumnName = "name")
    List<UserRights> RoleRights;
}
