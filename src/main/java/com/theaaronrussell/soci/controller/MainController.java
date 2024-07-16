package com.theaaronrussell.soci.controller;

import com.theaaronrussell.soci.security.AppUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String showHome(@AuthenticationPrincipal AppUserDetails user, Model model) {
        model.addAttribute("user", user);
        return "home";
    }

}
