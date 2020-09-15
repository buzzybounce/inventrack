package com.inventorytracker.InventoryTracker.controllers;

import com.inventorytracker.InventoryTracker.data.UserRepository;
import com.inventorytracker.InventoryTracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

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

}
