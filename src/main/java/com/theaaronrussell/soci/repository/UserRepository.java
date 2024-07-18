package com.theaaronrussell.soci.repository;

import com.theaaronrussell.soci.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {

    @EntityGraph(attributePaths = "roles")
    Optional<User> findEagerByUsername(String username);

}
