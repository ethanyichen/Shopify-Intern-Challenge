/*
 * Copyright Yichen Zhang 2022.
 */

package com.inventory.repository.impl;

import com.mongodb.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GroupMongoRepository implements GroupRepository{
    MongoClient mongoClient;
    DB database;

    public GroupMongoRepository() {
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDB("InventoryMongoDb");
        database.createCollection("groups", null);
    }

    @Override
    public UUID addGroup(String name) {
        DBCollection collection = database.getCollection("groups");
        BasicDBObject document = new BasicDBObject();
        UUID groupID = UUID.randomUUID();
        document.put("groupID", groupID.toString());
        document.put("name", name);
        collection.insert(document);
        return  groupID;
    }

    @Override
    public String getGroupByID(UUID groupID) {
        DBCollection collection = database.getCollection("groups");
        BasicDBObject query = new BasicDBObject();
        query.put("groupID", groupID.toString());
        DBObject obj = collection.findOne(query);
        if(obj == null) {
            return "";
        }
        return obj.toString();
    }


    @Override
    public List<String> showMembers(UUID groupID) {
        DBCollection collection = database.getCollection("items");
        BasicDBObject query = new BasicDBObject();
        query.put("groupID", groupID.toString());
        List<String> res = new ArrayList<>();
        List<DBObject> members = collection.find(query).toArray();
        for(DBObject member : members) {
            res.add(member.toString());
        }
        return res;
    }
}
