package com.theaaronrussell.soci.controller;

import com.theaaronrussell.soci.dto.UserDto;
import com.theaaronrussell.soci.exception.UserNotFoundException;
import com.theaaronrussell.soci.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showHome(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        log.trace("Received GET request for /");
        log.debug("Currently logged in user: {}", userDetails.getUsername());
        UserDto user;
        try {
            user = userService.getUser(userDetails.getUsername());
            log.debug("Found details for user {} {} (username: {})", user.getFirstName(), user.getLastName(), user.getUsername());
        } catch (UserNotFoundException e) {
            log.warn("User '{}' was not found in the database", userDetails.getUsername());
            return "errors/usernotfound";
        }
        model.addAttribute("user", user);
        return "home";
    }

}
