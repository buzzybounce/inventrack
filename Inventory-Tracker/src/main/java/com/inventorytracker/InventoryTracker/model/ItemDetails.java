package com.inventorytracker.InventoryTracker.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class ItemDetails extends AbstractEntity {

    @OneToOne (mappedBy = "itemDetails")
    private Item item;

    @Size(max = 500, message = "Item Description too long")
    private String itemDescription;

    private int quantity;

    public ItemDetails () {}

    public ItemDetails (String itemDescription, int quantity) {

        this.itemDescription = itemDescription;
        this.quantity = quantity;

    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
