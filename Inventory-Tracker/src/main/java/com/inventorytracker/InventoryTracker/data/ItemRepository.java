package com.inventorytracker.InventoryTracker.data;

import com.inventorytracker.InventoryTracker.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository <Item, Integer> {
}
