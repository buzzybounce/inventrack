package com.inventorytracker.InventoryTracker.controllers;

import com.inventorytracker.InventoryTracker.data.ItemCategoryRepository;
import com.inventorytracker.InventoryTracker.data.ItemRepository;
import com.inventorytracker.InventoryTracker.data.UserRepository;
import com.inventorytracker.InventoryTracker.model.Item;
import com.inventorytracker.InventoryTracker.model.ItemCategory;
import com.inventorytracker.InventoryTracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemCategoryRepository itemCategoryRepository;

    @Autowired
    AuthenticationController authenticationController;

    @GetMapping
    public String index (HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);

        model.addAttribute("user", user.getUsername());
        model.addAttribute("title", "Home Page");

        return "index";

    }

    @GetMapping("view/{itemId}")
    public String displayItem(HttpServletRequest request, Model model, @PathVariable int itemId) {

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);

        model.addAttribute("user", user.getUsername());

        Optional optItem = itemRepository.findById(itemId);
        if (optItem.isPresent()) {

            Item item = (Item) optItem.get();
            model.addAttribute("item", item);
            return "view";

        }

        else {

            return "redirect:../";

        }

    }

    @GetMapping ("edit/{itemId}")
    public String displayEditForm (HttpServletRequest request, Model model, @PathVariable int itemId) {

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);

        model.addAttribute("user", user.getUsername());

        Optional optItem = itemRepository.findById(itemId);
        if (optItem.isPresent()) {

            Item item = (Item) optItem.get();
            model.addAttribute("categories", itemCategoryRepository.findAll());
            model.addAttribute("item", item);
            return "edit";

        }

        else {

            return "redirect:../";

        }

    }

    @PostMapping ("edit/{itemId}")
    public String processEditForm (HttpServletRequest request, @PathVariable int itemId, @ModelAttribute Item updatedItem, Model model) {

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);

        model.addAttribute("user", user.getUsername());

        Item item = itemRepository.findById(itemId).get();

        item.setName(updatedItem.getName());
        item.setItemCategory(updatedItem.getItemCategory());
        item.setItemDetails(updatedItem.getItemDetails());
        item.setLastModified(user.getUsername());

        itemRepository.save(item);

        return "redirect:/view/" + item.getId();

    }

    @GetMapping ("delete/{itemId}")
    public String deleteItem (@PathVariable int itemId) {

        Optional<Item> result = itemRepository.findById(itemId);

        if (result.isPresent()) {

            itemRepository.deleteById(itemId);

        }

        return "redirect:/list";

    }

}
