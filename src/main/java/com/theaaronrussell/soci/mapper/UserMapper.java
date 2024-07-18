package com.theaaronrussell.soci.mapper;

import com.theaaronrussell.soci.dto.UserDto;
import com.theaaronrussell.soci.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = UserFactory.class)
public interface UserMapper {

    @Mapping(target = "authorities", ignore = true)
    org.springframework.security.core.userdetails.User userEntityToUserDetails(User user);

    UserDto userToUserDto(User user);

}
