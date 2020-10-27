package com.inventorytracker.InventoryTracker.controllers;

import com.inventorytracker.InventoryTracker.data.ItemCategoryRepository;
import com.inventorytracker.InventoryTracker.data.ItemRepository;
import com.inventorytracker.InventoryTracker.data.UserRepository;
import com.inventorytracker.InventoryTracker.model.ItemCategory;
import com.inventorytracker.InventoryTracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "list")
public class ListController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemCategoryRepository itemCategoryRepository;

    @Autowired
    AuthenticationController authenticationController;

    @RequestMapping
    public String list (HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);

        model.addAttribute("user", user.getUsername());

        model.addAttribute("title", "List Items");
        model.addAttribute("items",itemRepository.findAll());
        return "list";

    }

}
