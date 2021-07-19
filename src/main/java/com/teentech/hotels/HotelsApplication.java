package com.teentech.hotels;

import com.teentech.hotels.model.Hotel;
import com.teentech.hotels.model.User;
import com.teentech.hotels.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.awt.print.Book;
import java.math.BigDecimal;

@SpringBootApplication
public class HotelsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelsApplication.class, args);
    }


    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            repository.save(new User("mariusc", bCryptPasswordEncoder.encode("mariuscpassword"),
                    "en", "mariusc@mariusc.ro"));
            repository.save(new User("paul", bCryptPasswordEncoder.encode("paulpassword"),
                    "en", "paul@paul.ro"));
            repository.save(new User("roberto", bCryptPasswordEncoder.encode("robertopassword"),
                    "en", "roberto@roberto.ro"));

            repository.save(new User("cipri", bCryptPasswordEncoder.encode("cipripassword"),
                    "en", "cipri@cipri.ro"));
            repository.save(new User("victor", bCryptPasswordEncoder.encode("victorpassword"),
                    "en", "victor@victor.ro"));
            repository.save(new User("tudor", bCryptPasswordEncoder.encode("tudorpassword"),
                    "en", "tudor@tudor.ro"));
        };
    }
}
