/*
 * Copyright Yichen Zhang 2022.
 */

package com.example.inventory.service.impl;

import com.example.inventory.service.GroupService;
import com.inventory.repository.impl.GroupRepository;

import java.util.List;
import java.util.UUID;

public class DefaultGroupService implements GroupService {

    private GroupRepository groupMongoRepository;

    public DefaultGroupService(GroupRepository groupMongoRepository) {
        this.groupMongoRepository = groupMongoRepository;
    }

    @Override
    public String addGroup(String name) {
        return groupMongoRepository.addGroup(name).toString();
    }

    @Override
    public String getGroupByID(UUID groupID) {
        String res = groupMongoRepository.getGroupByID(groupID);
        if(res.equals("")) {
            return "Not Found";
        }
        return res;
    }

    @Override
    public List<String> showMembers(UUID groupID) {
        return groupMongoRepository.showMembers(groupID);
    }
}
