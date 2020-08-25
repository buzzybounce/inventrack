package com.inventorytracker.InventoryTracker.data;

import com.inventorytracker.InventoryTracker.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <User, Integer> {

    User findByUsername (String username);

}
