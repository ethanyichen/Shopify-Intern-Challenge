/*
 * Copyright Yichen Zhang 2022.
 */

package com.inventory.repository.config;

import com.inventory.repository.ItemRepository;
import com.inventory.repository.impl.GroupMongoRepository;
import com.inventory.repository.impl.ItemMongoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InventoryRepositoryConfiguration {

    @Bean
    public ItemMongoRepository itemRepository() {
        return new ItemMongoRepository();
    }

    @Bean
    public GroupMongoRepository groupRepository() {
        return new GroupMongoRepository();
    }
}
