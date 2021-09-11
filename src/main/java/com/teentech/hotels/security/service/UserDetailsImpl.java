package com.teentech.hotels.security.service;


import java.util.*;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.teentech.hotels.model.UserRole;
import com.teentech.hotels.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String username;

    @JsonIgnore
    private String password;

    private String language;

    private String mail;

    private UserRole userRole;

    private Long roleId;

    private long hotelId;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String username, String password, String language, String mail, UserRole userRole,
                           Long roleId, long hotelId, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.language = language;
        this.mail = mail;
        this.userRole = userRole;
        this.roleId = roleId;
        this.hotelId = hotelId;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(@NotNull User user) {
        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName().toUpperCase()));
        return new UserDetailsImpl(
                user.getUsername(),
                user.getPassword(),
                user.getLanguage(),
                user.getMail(),
                user.getRole(),
                user.getRoleId(),
                user.getHotelId() , authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(username, user.getUsername());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (username == null ? 0 : username.hashCode());
        return hash;
    }
}