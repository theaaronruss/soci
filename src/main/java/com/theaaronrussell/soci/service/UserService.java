package com.theaaronrussell.soci.service;

import com.theaaronrussell.soci.entity.User;
import com.theaaronrussell.soci.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

}
