package com.theaaronrussell.soci.service;

import com.theaaronrussell.soci.entity.Post;
import com.theaaronrussell.soci.entity.User;
import com.theaaronrussell.soci.repository.PostRepository;
import com.theaaronrussell.soci.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    /**
     * Create a new post in the database.
     *
     * @param username Username of the owner of the post.
     * @param content The content of the post.
     * @throws Exception If username is not found in database.
     */
    public void createPost(String username, String content) throws Exception {
        Optional<User> user = userRepository.findById(username);
        if (user.isEmpty()) throw new Exception("User does not exist");
        Post post = new Post();
        post.setOwner(user.get());
        post.setContent(content);
        postRepository.save(post);
    }

}
