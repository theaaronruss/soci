package com.theaaronrussell.soci.mapper;

import org.mapstruct.ObjectFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserFactory {

    @ObjectFactory
    org.springframework.security.core.userdetails.User userEntityToUserDetails(com.theaaronrussell.soci.entity.User user) {
        Set<SimpleGrantedAuthority> roles = new HashSet<>();
        for (String role : user.getRoles()) {
            roles.add(new SimpleGrantedAuthority(role));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
    }

}
