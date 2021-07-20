package com.teentech.hotels.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "rights")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRights implements Serializable {
    @Id
    @SequenceGenerator(name = "rights_id_seq", sequenceName = "rights_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rights_id_seq")
    private Long id;

    private String name;

    private String description;

    @ManyToMany(mappedBy = "rights")
    private Collection<UserRole> userRoles;
}
