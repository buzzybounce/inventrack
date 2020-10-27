package com.inventorytracker.InventoryTracker.data;

import com.inventorytracker.InventoryTracker.model.ItemCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCategoryRepository extends CrudRepository <ItemCategory, Integer> {
}
