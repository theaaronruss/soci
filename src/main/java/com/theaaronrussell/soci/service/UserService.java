package com.theaaronrussell.soci.service;

import com.theaaronrussell.soci.entity.User;
import com.theaaronrussell.soci.repository.FollowingRepository;
import com.theaaronrussell.soci.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final FollowingRepository followingRepository;

    @Autowired
    public UserService(UserRepository userRepository, FollowingRepository followingRepository) {
        this.userRepository = userRepository;
        this.followingRepository = followingRepository;
    }

    /**
     * Retrieve a user from the database.
     *
     * @param username Username of the user to retrieve.
     * @return The requested user from the database.
     * @throws Exception If user does not exist in database.
     */
    public User getUser(String username) throws Exception {
        Optional<User> user = userRepository.findById(username);
        if (user.isEmpty()) throw new Exception("User does not exist");
        return user.get();
    }

    /**
     * Get the number of users a user is following.
     *
     * @param username Username of the user to retrieve following count for.
     * @return The count of users the user is following.
     */
    public int getNumFollowing(String username) {
        return followingRepository.countByUsername(username);
    }

    /**
     * Get the number of followers a user has.
     *
     * @param username The username of the user to retrieve follower count for.
     * @return The count of followers the user has.
     */
    public int getNumFollowers(String username) {
        return followingRepository.countByFollowing(username);
    }

}
