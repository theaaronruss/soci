package com.theaaronrussell.soci.mapper;

import com.theaaronrussell.soci.entity.User;
import com.theaaronrussell.soci.security.AppUserDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "authorities", ignore = true)
    AppUserDetails userToAppUserDetails(User user);

}
