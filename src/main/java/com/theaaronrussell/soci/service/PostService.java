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
     * @return The newly created post.
     * @throws Exception If username is not found in database.
     */
    public Post createPost(String username, String content) throws Exception {
        Optional<User> user = userRepository.findById(username);
        if (user.isEmpty()) throw new Exception("User does not exist");
        Post post = new Post();
        post.setOwner(user.get());
        post.setContent(content);
        return postRepository.save(post);
    }

    /**
     * Retrieve a post from the database.
     *
     * @param id ID of the post to retrieve.
     * @return The requested post.
     * @throws Exception If post is not found in the database.
     */
    public Post getPost(long id) throws Exception {
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) throw new Exception("Post does not exist");
        return post.get();
    }

    /**
     * Retrieve all posts created by a specified user.
     *
     * @param username Username of the user to retrieve posts for.
     * @return A list of posts created by the user with {@code username}.
     */
    public Iterable<Post> getUserPosts(String username) {
        return postRepository.findByOwnerUsername(username);
    }

    /**
     * Retrieve posts from the users a user is following.
     *
     * @param username Username of the user to retrieve posts for based on their following list.
     * @return A list of posts based on whom the requested user is following.
     * @throws Exception If the user does not exist.
     */
    public Iterable<Post> getFollowingPosts(String username) throws Exception {
        Optional<User> user = userRepository.findById(username);
        if (user.isEmpty()) throw new Exception("User does not exist");
        return postRepository.findByOwnerUsernameIn(user.get().getFollowing());
    }

}
