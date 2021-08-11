package com.teentech.hotels;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;


@Log4j2
@SpringBootApplication
public class HotelsApplication {


    public static void main(String[] args) {
        SpringApplication.run(HotelsApplication.class, args);
    }

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create().username(System.getenv("HOTEL_DB_USER")).password(System.getenv("HOTEL_DB_PASSWORD")).url(System.getenv("HOTEL_DB_URL"))
                .driverClassName("org.postgresql.Driver").build();
    }

}
