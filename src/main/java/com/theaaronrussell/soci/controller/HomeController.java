package com.theaaronrussell.soci.controller;

import com.theaaronrussell.soci.dto.UserDto;
import com.theaaronrussell.soci.exception.UserNotFoundException;
import com.theaaronrussell.soci.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showHome(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        UserDto user;
        try {
            user = userService.getUser(userDetails.getUsername());
        } catch (UserNotFoundException e) {
            return "errors/usernotfound";
        }
        model.addAttribute("user", user);
        return "home";
    }

}
