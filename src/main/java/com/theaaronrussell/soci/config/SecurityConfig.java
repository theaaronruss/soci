package com.theaaronrussell.soci.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(DataSource dataSource) {
        UserDetails user = User.builder().username("user").password("{noop}password").roles("USER").build();
        return new InMemoryUserDetailsManager(user);
    }

}
