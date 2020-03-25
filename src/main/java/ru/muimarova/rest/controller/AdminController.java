package ru.muimarova.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.muimarova.rest.model.User;
import ru.muimarova.rest.service.UserService;


@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping
    public String common(Model model) {
        return "common";
    }

    @PostMapping("/saveUser")
    public String saveCustomer(@ModelAttribute("user") User user) {
        return "redirect:/admin";
    }

}
