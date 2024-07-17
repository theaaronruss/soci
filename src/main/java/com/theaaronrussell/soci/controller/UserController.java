package com.theaaronrussell.soci.controller;

import com.theaaronrussell.soci.entity.Post;
import com.theaaronrussell.soci.entity.User;
import com.theaaronrussell.soci.service.PostService;
import com.theaaronrussell.soci.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PostService postService;

    @Autowired
    public UserController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/{username}")
    public String showUser(@PathVariable String username, Model model) {
        User user;
        try {
            user = userService.getUser(username);
        } catch (Exception e) {
            model.addAttribute("username", username);
            return "errors/usernotfound";
        }
        Iterable<Post> posts = postService.getUserPosts(username);
        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
        model.addAttribute("followers", userService.getNumFollowers(username));
        model.addAttribute("following", userService.getNumFollowing(username));
        return "user";
    }

}
