/*
 * Copyright Yichen Zhang 2022.
 */

package com.inventory.repository.impl;


import com.inventory.repository.ItemRepository;
import com.mongodb.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemMongoRepository implements ItemRepository {
    MongoClient mongoClient;
    DB database;

    @Autowired
    GroupMongoRepository groupMongoRepository;

    public ItemMongoRepository() {
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDB("InventoryMongoDb");
        database.createCollection("items", null);
    }

    @Override
    public void addItem(UUID itemID, String name) {
        DBCollection collection = database.getCollection("items");
        BasicDBObject document = new BasicDBObject();
        document.put("itemID", itemID.toString());
        document.put("name", name);
        document.put("quantity", 0);
        document.put("groupID", "");
        collection.insert(document);
    }

    @Override
    public String assignItemToGroup(UUID itemID, UUID groupID) {
        if(groupMongoRepository.getGroupByID(groupID).equals("")) {
            return "Group Not Found";
        }
        DBCollection collection = database.getCollection("items");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("itemID", itemID.toString());

        BasicDBObject updateGroup = new BasicDBObject();
        updateGroup.put("groupID", groupID.toString());
        BasicDBObject updateQuery = new BasicDBObject();
        updateQuery.put("$set", updateGroup);
        WriteResult result = collection.update(searchQuery, updateQuery);
        if(!result.wasAcknowledged()) {
            return "Item Not Found";
        } else {
            return "Acknowledged";
        }
    }

    @Override
    public String removeItemFromGroup(UUID itemID) {
        DBCollection collection = database.getCollection("items");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("itemID", itemID.toString());

        BasicDBObject updateGroup = new BasicDBObject();
        updateGroup.put("groupID", "");
        BasicDBObject updateQuery = new BasicDBObject();
        updateQuery.put("$set", updateGroup);
        WriteResult result = collection.update(searchQuery, updateQuery);
        if(!result.wasAcknowledged()) {
            return "Item Not Found";
        } else {
            return "Acknowledged";
        }
    }


    @Override
    public void deleteItemById(UUID itemID) {
        DBCollection collection = database.getCollection("items");
        BasicDBObject query = new BasicDBObject();
        query.put("itemID", itemID);

        collection.remove(query);
    }

    @Override
    public List<String> getItems() {
        DBCollection collection = database.getCollection("items");
        List<String> res = new ArrayList<>();
        List<DBObject> items = collection.find().toArray();
        for(DBObject item : items) {
            res.add(item.toString());
        }
        return res;
    }

    @Override
    public String getItemById(UUID itemID) {
        DBCollection collection = database.getCollection("items");
        BasicDBObject query = new BasicDBObject();
        query.put("itemID", itemID.toString());
        DBObject obj = collection.findOne(query);
        if(obj == null) {
            return "";
        }
        return obj.toString();
    }

    @Override
    public String updateItemQuantity(UUID itemID, int newQuantity) {
        DBCollection collection = database.getCollection("items");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("itemID", itemID.toString());

        BasicDBObject updateQuantity = new BasicDBObject();
        updateQuantity.put("quantity", newQuantity);
        BasicDBObject updateQuery = new BasicDBObject();
        updateQuery.put("$set", updateQuantity);
        WriteResult result = collection.update(searchQuery, updateQuery);
        if(!result.wasAcknowledged()) {
            return "";
        } else {
            return "Acknowledged";
        }
    }
}
