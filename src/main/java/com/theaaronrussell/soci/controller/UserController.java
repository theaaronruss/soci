package com.theaaronrussell.soci.controller;

import com.theaaronrussell.soci.dto.NewUserDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/signup")
    public String showSignUp(@AuthenticationPrincipal UserDetails user, Model model) {
        if (user != null) return "redirect:/";
        model.addAttribute("newUser", new NewUserDto());
        return "signup";
    }

}
