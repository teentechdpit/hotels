package com.teentech.hotels;

import com.teentech.hotels.model.*;
import com.teentech.hotels.repository.UserRepository;
import com.teentech.hotels.repository.UserRightsRepository;
import com.teentech.hotels.repository.UserRoleRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

@SpringBootApplication
@Log4j2
public class HotelsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelsApplication.class, args);
    }


    @Bean
    CommandLineRunner initDatabase(UserRepository repository, UserRightsRepository userRightsRepository, UserRoleRepository userRoleRepository) {
        return args -> {

           BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
           bCryptPasswordEncoder.encode("password");

//            log.info(bCryptPasswordEncoder.encode("password"));
        };
    }
}
