package com.teentech.hotels;

import com.teentech.hotels.model.User;
import com.teentech.hotels.model.UserRights;
import com.teentech.hotels.model.UserRoles;
import com.teentech.hotels.repository.UserRepository;
import com.teentech.hotels.repository.UserRightsRepository;
import com.teentech.hotels.repository.UserRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

@SpringBootApplication
public class HotelsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelsApplication.class, args);
    }


    @Bean
    CommandLineRunner initDatabase(UserRepository repository, UserRightsRepository userRightsRepository, UserRoleRepository userRoleRepository) {
        return args -> {

           /* BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

            List<UserRights> UserRigtsTestList = new ArrayList<UserRights>();

            UserRights Rights = new UserRights("Manager","Description");
            userRightsRepository.save(Rights);

            UserRigtsTestList.add(Rights);
            UserRoles rol = new UserRoles("Manager","bLA",UserRigtsTestList);

            userRoleRepository.save(rol);

            repository.save(new User("mariusc",bCryptPasswordEncoder.encode("mariuscpassword"),
                    "en", "mariusc@mariusc.ro",rol));
            repository.save(new User("paul",bCryptPasswordEncoder.encode("paulpassword"),
                    "en", "paul@paul.ro",rol));
            repository.save(new User("roberto", bCryptPasswordEncoder.encode("robertopassword"),
                    "en", "roberto@roberto.ro",rol));

            repository.save(new User("cipri", bCryptPasswordEncoder.encode("cipripassword"),
                    "en", "cipri@cipri.ro",rol));
            repository.save(new User("victor",bCryptPasswordEncoder.encode("victorpassword"),
                    "en", "victor@victor.ro",rol));
            repository.save(new User("tudor", bCryptPasswordEncoder.encode("tudorpassword"),
                    "en", "tudor@tudor.ro",rol));*/

        };
    }
}
