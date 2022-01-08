/*
 * Copyright Yichen Zhang 2022.
 */

package com.inventory.repository;


import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemRepository {

    void addItem(UUID itemID, String name);

    String assignItemToGroup(UUID itemID, UUID groupID);

    String removeItemFromGroup(UUID itemID);

    void deleteItemById(UUID itemID);

    List<String> getItems();

    String getItemById(UUID itemID);

    String updateItemQuantity(UUID itemID, int newQuantity);
}
