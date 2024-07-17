package com.theaaronrussell.soci.controller;

import com.theaaronrussell.soci.entity.Post;
import com.theaaronrussell.soci.security.AppUserDetails;
import com.theaaronrussell.soci.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final PostService postService;

    @Autowired
    public MainController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String showHome(@AuthenticationPrincipal AppUserDetails user, Model model) {
        model.addAttribute("user", user);
        Iterable<Post> posts;
        try {
            posts = postService.getFollowingPosts(user.getUsername());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("posts", posts);
        return "home";
    }

}
