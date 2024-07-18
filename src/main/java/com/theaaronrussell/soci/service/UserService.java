package com.theaaronrussell.soci.service;

import com.theaaronrussell.soci.dto.UserDto;
import com.theaaronrussell.soci.entity.User;
import com.theaaronrussell.soci.exception.UserNotFoundException;
import com.theaaronrussell.soci.mapper.UserMapper;
import com.theaaronrussell.soci.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /**
     * Check if a user exists in the database.
     *
     * @param username Username of the user to check for.
     * @return True if user exists, otherwise false.
     */
    public boolean doesUserExists(String username) {
        return userRepository.existsById(username);
    }

    /**
     * Retrieve a user from the database.
     *
     * @param username Username of the user to retrieve.
     * @return The requested user.
     * @throws UserNotFoundException If user is not found in the database.
     */
    public UserDto getUser(String username) throws UserNotFoundException {
        Optional<User> userEntity = userRepository.findById(username);
        if (userEntity.isEmpty()) throw new UserNotFoundException(username);
        return userMapper.userToUserDto(userEntity.get());
    }

}
