package com.alexeysmoliagin.springboot.sportclub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//@EnableCaching
public class SportClubApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportClubApplication.class, args);
    }
}