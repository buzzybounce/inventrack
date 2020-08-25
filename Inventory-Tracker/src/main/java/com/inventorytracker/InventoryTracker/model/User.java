package com.inventorytracker.InventoryTracker.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User extends AbstractEntity {

    @NotNull (message = "Username cannot be blank.")
    @Size
    private String username;

    @NotNull (message = "Password cannot be blank.")
    private String pwHash;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User () {}

    public User (String username, String password) {

        this.username = username;
        this.pwHash = encoder.encode(password);

    }

    public String getUsername() {

        return username;

    }

    public boolean isMatchingPassword (String password) {

        return encoder.matches(password, pwHash);

    }


}
