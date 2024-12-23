package com.example.inventorymanagerproj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class InventoryManagerProjApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryManagerProjApplication.class, args);
    }

}
