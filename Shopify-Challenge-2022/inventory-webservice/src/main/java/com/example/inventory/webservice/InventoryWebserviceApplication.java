/*
 * Copyright Yichen Zhang 2022.
 */

package com.example.inventory.webservice;

import com.example.inventory.webservice.Controller.ItemController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@ComponentScan(basePackages = {"com.example.inventory.webservice.Controller"})
@SpringBootApplication
public class InventoryWebserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryWebserviceApplication.class, args);
    }


}
