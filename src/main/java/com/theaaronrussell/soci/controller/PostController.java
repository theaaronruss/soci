package com.theaaronrussell.soci.controller;

import com.theaaronrussell.soci.entity.Post;
import com.theaaronrussell.soci.security.AppUserDetails;
import com.theaaronrussell.soci.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts/{id}")
    public String showPost(@PathVariable long id, Model model) {
        Post post;
        try {
            post = postService.getPost(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("post", post);
        return "post";
    }

    @PostMapping("/post")
    public String processNewPost(@AuthenticationPrincipal AppUserDetails user,
                                 @ModelAttribute(name = "content") String content) {
        Post post;
        try {
            post = postService.createPost(user.getUsername(), content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/posts/" + post.getId();
    }

}
