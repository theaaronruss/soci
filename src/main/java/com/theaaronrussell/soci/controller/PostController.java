package com.theaaronrussell.soci.controller;

import com.theaaronrussell.soci.security.AppUserDetails;
import com.theaaronrussell.soci.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post")
    public String processNewPost(@AuthenticationPrincipal AppUserDetails user,
                                 @ModelAttribute(name = "content") String content) {
        try {
            postService.createPost(user.getUsername(), content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/";
    }

}
