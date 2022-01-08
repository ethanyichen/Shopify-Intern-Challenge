/*
 * Copyright Yichen Zhang 2022.
 */

package com.example.inventory.service.config;

import com.example.inventory.service.impl.DefaultGroupService;
import com.example.inventory.service.impl.DefaultItemService;
import com.inventory.repository.ItemRepository;
import com.inventory.repository.impl.GroupRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.inventory.repository"})
public class ItemServiceConfiguration {
    @Bean
    public DefaultItemService itemService(ItemRepository itemRepository) {
        return new DefaultItemService(itemRepository);
    }

    @Bean
    public DefaultGroupService groupService(GroupRepository groupRepository) {
        return new DefaultGroupService(groupRepository);
    }

}
