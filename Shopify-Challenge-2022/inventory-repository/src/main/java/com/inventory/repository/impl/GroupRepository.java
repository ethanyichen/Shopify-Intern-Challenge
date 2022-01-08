/*
 * Copyright Yichen Zhang 2022.
 */

package com.inventory.repository.impl;

import java.util.List;
import java.util.UUID;

public interface GroupRepository {
    UUID addGroup(String name);

    String getGroupByID(UUID groupID);

    List<String> showMembers(UUID groupID);
}
