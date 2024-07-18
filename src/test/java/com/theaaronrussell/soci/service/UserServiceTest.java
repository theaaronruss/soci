package com.theaaronrussell.soci.service;

import com.theaaronrussell.soci.dto.UserDto;
import com.theaaronrussell.soci.entity.User;
import com.theaaronrussell.soci.exception.UserNotFoundException;
import com.theaaronrussell.soci.mapper.UserMapper;
import com.theaaronrussell.soci.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserService userService;

    @Test
    void retrieveUser() throws UserNotFoundException {
        User user = new User("mjones", "{noop}password", "Matt", "Jones", null);
        UserDto userDto = new UserDto("mjones", "{noop}password", "Matt", "Jones");
        when(userRepository.findById("mjones")).thenReturn(Optional.of(user));
        when(userMapper.userToUserDto(user)).thenReturn(userDto);
        UserDto result = userService.getUser("mjones");
        assertEquals(userDto, result);
    }

    @Test
    void retrieveUserNotFound() {
        when(userRepository.findById("mjones")).thenReturn(Optional.empty());
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> userService.getUser("mjones"));
        assertEquals("User mjones not found in database", exception.getMessage());
    }

}