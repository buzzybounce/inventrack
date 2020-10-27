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
@RequestMapping("items")
public class ItemController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemCategoryRepository itemCategoryRepository;

    @Autowired
    AuthenticationController authenticationController;

    @GetMapping("create-item")
    public String displayCreateItemForm (HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);

        model.addAttribute("user", user.getUsername());
        model.addAttribute("title", "Add Item");
        model.addAttribute("categories", itemCategoryRepository.findAll());
        model.addAttribute(new Item());
        return "items/create-item";

    }

    @PostMapping("create-item")
    public String processCreateItemForm (HttpServletRequest request,
                                         @ModelAttribute @Valid Item newItem,
                                         Errors errors, Model model) {

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);

        model.addAttribute("user", user.getUsername());

        if (errors.hasErrors()) {

            model.addAttribute("title", "Add Item");
            model.addAttribute("categories", itemCategoryRepository.findAll());
            return "items/create-item";

        }

        newItem.setLastModified(user.getUsername());
        itemRepository.save(newItem);

        model.addAttribute("title", "Items");
        return "redirect:/list";

    }

    @GetMapping("create-category")
    public String displayAddCategoryForm (HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);

        model.addAttribute("user", user.getUsername());
        model.addAttribute("title", "Add Category");
        model.addAttribute(new ItemCategory());

        return "items/create-category";

    }

    @PostMapping("create-category")
    public String processAddCategoryForm (HttpServletRequest request,
                                          @ModelAttribute @Valid ItemCategory newItemCategory,
                                          Errors errors, Model model) {

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);

        model.addAttribute("user", user.getUsername());

        if (errors.hasErrors()) {

            model.addAttribute("title", "Add Category");
            return "items/create-category";

        }

        model.addAttribute("title", "Add Category");
        itemCategoryRepository.save(newItemCategory);

        return "redirect:/items/create-item";

    }

}
