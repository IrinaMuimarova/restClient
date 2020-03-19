package ru.muimarova.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class userController {

    @GetMapping
    public String common(){
        return "common";
    }

    @PostMapping("/saveUser")
    public String saveCustomer() {
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete() {
        return "redirect:/";
    }

    @GetMapping("/user")
    public String userCard(){
        return "user";
    }
}
