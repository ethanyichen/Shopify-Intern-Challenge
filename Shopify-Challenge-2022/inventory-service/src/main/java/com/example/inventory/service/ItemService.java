/*
 * Copyright Yichen Zhang 2022.
 */

package com.example.inventory.service;

import com.inventory.domain.model.Item;

import java.util.List;
import java.util.UUID;

public interface ItemService {
    UUID addItem(String name);

    void deleteItemById(UUID itemID);

    String getItemById(UUID itemID);

    List<String> getItems();

    String assignItemToGroup(UUID itemID, UUID groupID);

    String removeItemFromGroup(UUID itemID);

    String updateItemQuantity(UUID itemID, int newQuantity);
}
