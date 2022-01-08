/*
 * Copyright Yichen Zhang 2022.
 */

package com.example.inventory.webservice.config;

import com.example.inventory.service.ItemService;
import com.example.inventory.service.impl.DefaultItemService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
public class InventoryWebserviceConfiguration{
//    @Bean
//    public ItemService itemService() {
//        return new DefaultItemService();
//    }
}
