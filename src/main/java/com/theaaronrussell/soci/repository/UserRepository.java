package com.theaaronrussell.soci.repository;

import com.theaaronrussell.soci.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
