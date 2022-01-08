/*
 * Copyright Yichen Zhang 2022.
 */

package com.inventory.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ItemGroup {
    private UUID groupID;
    private String groupName;
}
