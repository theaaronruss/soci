package com.theaaronrussell.soci.service;

import com.theaaronrussell.soci.entity.User;
import com.theaaronrussell.soci.mapper.UserMapper;
import com.theaaronrussell.soci.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserMapper userMapper;

    @InjectMocks
    CustomUserDetailsService userDetailsService;

    @Test
    void loadUser() {
        User user = new User("mjones", "{noop}password", "Matt", "Jones", null);
        org.springframework.security.core.userdetails.User userDetails =
                new org.springframework.security.core.userdetails.User("mjones", "{noop}password", new ArrayList<>());
        when(userRepository.findEagerByUsername("mjones")).thenReturn(Optional.of(user));
        when(userMapper.userEntityToUserDetails(user)).thenReturn(userDetails);
        UserDetails result = userDetailsService.loadUserByUsername("mjones");
        assertEquals("mjones", result.getUsername());
        assertEquals("{noop}password", result.getPassword());
    }

    @Test
    void failToLoadUser() {
        when(userRepository.findEagerByUsername("mjones")).thenReturn(Optional.empty());
        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class,
                () -> userDetailsService.loadUserByUsername("mjones"));
        assertEquals("Username mjones not found in database", exception.getMessage());
    }

}