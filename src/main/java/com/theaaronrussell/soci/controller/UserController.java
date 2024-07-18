package com.theaaronrussell.soci.controller;

import com.theaaronrussell.soci.dto.NewUserFormDto;
import com.theaaronrussell.soci.service.CustomUserDetailsService;
import com.theaaronrussell.soci.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final CustomUserDetailsService userDetailsService;
    private final UserService userService;

    @Autowired
    public UserController(CustomUserDetailsService userDetailsService, UserService userService) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String showSignUp(@AuthenticationPrincipal UserDetails user, Model model) {
        if (user != null) return "redirect:/";
        model.addAttribute("newUser", new NewUserFormDto());
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignUp(HttpServletRequest request, @AuthenticationPrincipal UserDetails user,
                                @ModelAttribute("newUser") @Valid NewUserFormDto newUser, BindingResult bindingResult,
                                Model model) {
        if (user != null) return "redirect:/";
        if (bindingResult.hasErrors()) {
            model.addAttribute("newUser", newUser);
            return "signup";
        }
        try {
            userDetailsService.registerNewUser(newUser);
            request.login(newUser.getUsername(), newUser.getPassword());
        } catch (Exception e) {
            return "redirect:/signup";
        }
        return "redirect:/";
    }

}
