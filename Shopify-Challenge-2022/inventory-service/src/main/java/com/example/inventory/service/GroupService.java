/*
 * Copyright Yichen Zhang 2022.
 */

package com.example.inventory.service;

import java.util.List;
import java.util.UUID;

public interface GroupService {
    String addGroup(String name);

    String getGroupByID(UUID groupID);

    List<String> showMembers(UUID groupID);
}
