/*
 * Copyright Yichen Zhang 2022.
 */

package com.example.inventory.service.impl;

import com.example.inventory.service.ItemService;
import com.inventory.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DefaultItemService implements ItemService {

    private ItemRepository itemMongoRepository;

    public DefaultItemService(ItemRepository itemRepository) {
        this.itemMongoRepository = itemRepository;
    }

    @Override
    public UUID addItem(String name) {
        UUID itemID = UUID.randomUUID();
        itemMongoRepository.addItem(itemID, name);
        return itemID;
    }

    @Override
    public void deleteItemById(UUID itemID) {
        itemMongoRepository.deleteItemById(itemID);
    }

    @Override
    public String getItemById(UUID itemID) {
        return itemMongoRepository.getItemById(itemID);
    }

    @Override
    public List<String> getItems() {
        return itemMongoRepository.getItems();
    }

    @Override
    public String assignItemToGroup(UUID itemID, UUID groupID) {
        return itemMongoRepository.assignItemToGroup(itemID, groupID);
    }

    @Override
    public String removeItemFromGroup(UUID itemID) {
        return itemMongoRepository.removeItemFromGroup(itemID);
    }

    @Override
    public String updateItemQuantity(UUID itemID, int newQuantity) {
        String res = itemMongoRepository.updateItemQuantity(itemID, newQuantity);
        if(res.equals("")) {
            return "Item Not Found";
        }
        return res;
    }
}
