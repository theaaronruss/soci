package com.theaaronrussell.soci.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = UserFactory.class)
public interface UserMapper {

    @Mapping(target = "authorities", ignore = true)
    org.springframework.security.core.userdetails.User userEntityToUserDetails(com.theaaronrussell.soci.entity.User user);

}
