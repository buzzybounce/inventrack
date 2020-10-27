package com.inventorytracker.InventoryTracker.controllers;

import com.inventorytracker.InventoryTracker.data.ItemCategoryRepository;
import com.inventorytracker.InventoryTracker.data.ItemRepository;
import com.inventorytracker.InventoryTracker.data.UserRepository;
import com.inventorytracker.InventoryTracker.model.Item;
import com.inventorytracker.InventoryTracker.model.ItemCategory;
import com.inventorytracker.InventoryTracker.model.ItemData;
import com.inventorytracker.InventoryTracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping (value = "search")
public class SearchController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemCategoryRepository itemCategoryRepository;

    @Autowired
    AuthenticationController authenticationController;

    @RequestMapping
    public String search (HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);

        model.addAttribute("user", user.getUsername());
        model.addAttribute("title", "Search");
        return "search";

    }

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchTerm) {

        Iterable<Item> items;

        items = ItemData.findByColumnAndValue("all", searchTerm, itemRepository.findAll());

        model.addAttribute("title", "Jobs with All: " + searchTerm);
        model.addAttribute("items", items);

        return "search";

    }

}
