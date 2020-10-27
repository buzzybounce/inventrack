package com.inventorytracker.InventoryTracker.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ItemCategory extends AbstractEntity {

    @Size(max = 30, message = "Name must be lesser than 30 characters.")
    private String name;

    @OneToMany(mappedBy = "itemCategory")
    private final List<Item> items = new ArrayList<>();

    public ItemCategory () {}

    public ItemCategory(String name) {

        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getEvents() {
        return items;
    }

    @Override
    public String toString() { return name; }

}

