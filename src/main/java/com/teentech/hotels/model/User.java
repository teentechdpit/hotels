package com.teentech.hotels.model;


import com.teentech.hotels.model.UserRoles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


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

    private String roleName;

    @OneToOne
    @JoinColumn(name = "roleName",referencedColumnName = "name")
    private UserRoles role;
}

