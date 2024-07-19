package com.theaaronrussell.soci.controller;

import com.theaaronrussell.soci.dto.NewUserFormDto;
import com.theaaronrussell.soci.dto.UserLoginDto;
import com.theaaronrussell.soci.exception.UsernameAlreadyExistsException;
import com.theaaronrussell.soci.service.CustomUserDetailsService;
import com.theaaronrussell.soci.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public UserController(CustomUserDetailsService userDetailsService, UserService userService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/signup")
    public String showSignUp(@AuthenticationPrincipal UserDetails user, Model model) {
        log.trace("Received GET request for /signup");
        if (user != null) {
            log.info("User is already signed in, redirecting to home page");
            return "redirect:/";
        }
        model.addAttribute("newUser", new NewUserFormDto());
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignUp(HttpServletRequest request, @AuthenticationPrincipal UserDetails user,
                                @ModelAttribute("newUser") @Valid NewUserFormDto newUser, BindingResult bindingResult,
                                Model model) {
        log.trace("Received POST request for /signup");
        if (user != null) {
            log.info("User is already signed in, redirecting to home page");
            return "redirect:/";
        }
        if (bindingResult.hasErrors()) {
            log.debug("Submitted new user form contained {} errors", bindingResult.getErrorCount());
            model.addAttribute("newUser", newUser);
            return "signup";
        }
        try {
            userDetailsService.registerNewUser(newUser);
            log.info("New user registered: {} {} (username: {})", newUser.getFirstName(), newUser.getLastName(), newUser.getUsername());
            request.login(newUser.getUsername(), newUser.getPassword() + 'd');
        } catch (UsernameAlreadyExistsException e) {
            log.warn("Failed to register new user: {}", e.getMessage());
            return "redirect:/signup";
        } catch (ServletException e) {
            log.warn("Failed to log new user in: {}", e.getMessage());
            return "redirect:/signup";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        log.trace("Received GET request for /login");
        model.addAttribute("user", new UserLoginDto());
        return "login";
    }

}
