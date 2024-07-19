package com.theaaronrussell.soci.service;

import com.theaaronrussell.soci.config.Roles;
import com.theaaronrussell.soci.dto.NewUserFormDto;
import com.theaaronrussell.soci.entity.User;
import com.theaaronrussell.soci.exception.UsernameAlreadyExistsException;
import com.theaaronrussell.soci.mapper.UserMapper;
import com.theaaronrussell.soci.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.trace("Retrieving user details for '{}'", username);
        Optional<User> user = userRepository.findEagerByUsername(username);
        if (user.isEmpty()) {
            log.warn("Username '{}' not found in database", username);
            String error = String.format("Username %s not found in database", username);
            throw new UsernameNotFoundException(error);
        }
        return userMapper.userEntityToUserDetails(user.get());
    }

    /**
     * Add a new user to the database.
     *
     * @param newUserFormDto Details of the new user.
     * @throws UsernameAlreadyExistsException If the username of the user already exists in the database.
     */
    public void registerNewUser(NewUserFormDto newUserFormDto) throws UsernameAlreadyExistsException {
        log.info("Registering new user {} {} (username: {})", newUserFormDto.getFirstName(), newUserFormDto.getLastName(), newUserFormDto.getUsername());
        if (userRepository.existsById(newUserFormDto.getUsername())) {
            log.warn("User with username '{}' already exists", newUserFormDto.getUsername());
            throw new UsernameAlreadyExistsException();
        }
        String password = passwordEncoder.encode(newUserFormDto.getPassword());
        User newUser = userMapper.newUserFormDtoToUser(newUserFormDto);
        newUser.setPassword(password);
        newUser.setRoles(Set.of("ROLE_" + Roles.ROLE_USER));
        userRepository.save(newUser);
    }

}
