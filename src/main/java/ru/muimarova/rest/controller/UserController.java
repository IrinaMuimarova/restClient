package ru.muimarova.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.muimarova.rest.model.Role;
import ru.muimarova.rest.model.User;
import ru.muimarova.rest.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public String userCard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user;
        if (authentication instanceof OAuth2AuthenticationToken) {
            DefaultOidcUser defaultOidcUser = (DefaultOidcUser) authentication.getPrincipal();
            user = new User();
            user.setName((String) defaultOidcUser.getAttributes().get("name"));
            user.setLogin((String) defaultOidcUser.getAttributes().get("email"));
            Role role = new Role();
            role.setName("USER");
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
        } else {
            user = (User) authentication.getPrincipal();
        }
        model.addAttribute("userCurrent", user);
        return "user";
    }

}
