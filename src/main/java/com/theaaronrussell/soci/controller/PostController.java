package com.theaaronrussell.soci.controller;

import com.theaaronrussell.soci.entity.Post;
import com.theaaronrussell.soci.security.CustomUserDetails;
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
            return "errors/postnotfound";
        }
        model.addAttribute("post", post);
        return "post";
    }

    @PostMapping("/post")
    public String processNewPost(@AuthenticationPrincipal CustomUserDetails user,
                                 @ModelAttribute(name = "content") String content, Model model) {
        String username = user.getUsername();
        Post post;
        try {
            post = postService.createPost(username, content);
        } catch (Exception e) {
            model.addAttribute("username", username);
            return "errors/usernotfound";
        }
        return "redirect:/posts/" + post.getId();
    }

}
