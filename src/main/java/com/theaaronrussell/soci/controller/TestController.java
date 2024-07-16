package com.theaaronrussell.soci.controller;

import com.theaaronrussell.soci.security.AppUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test(@AuthenticationPrincipal AppUserDetails user) {
        return String.format("Your name is %s %s with roles %s", user.getFirstName(), user.getLastName(),
                user.getAuthorities());
    }

}
