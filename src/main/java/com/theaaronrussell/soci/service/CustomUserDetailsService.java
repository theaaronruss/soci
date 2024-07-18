package com.theaaronrussell.soci.service;

import com.theaaronrussell.soci.entity.User;
import com.theaaronrussell.soci.mapper.UserMapper;
import com.theaaronrussell.soci.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public CustomUserDetailsService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findEagerByUsername(username);
        if (user.isEmpty()) {
            String error = String.format("Username %s not found in database", username);
            throw new UsernameNotFoundException(error);
        }
        return userMapper.userEntityToUserDetails(user.get());
    }

}
