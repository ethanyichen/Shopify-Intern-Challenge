/*
 * Copyright Yichen Zhang 2022.
 */

package com.inventory.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Item {
    private UUID itemID;
    private String name;
    private int quantity = 0;

    public Item(UUID id, String name) {
        this.itemID = id;
        this.name = name;
    }
}
