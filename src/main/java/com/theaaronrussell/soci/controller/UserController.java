package com.theaaronrussell.soci.controller;

import com.theaaronrussell.soci.entity.Post;
import com.theaaronrussell.soci.entity.User;
import com.theaaronrussell.soci.repository.PostRepository;
import com.theaaronrussell.soci.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public UserController(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/{username}")
    public String showUser(@PathVariable String username, Model model) {
        Optional<User> userOptional = userRepository.findById(username);
        if (userOptional.isEmpty()) {
            model.addAttribute("username", username);
            return "errors/usernotfound";
        }
        User user = userOptional.get();
        Iterable<Post> posts = postRepository.findByOwnerUsername(user.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
        return "user";
    }

}
