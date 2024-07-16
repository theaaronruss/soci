package com.theaaronrussell.soci.repository;

import com.theaaronrussell.soci.entity.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

    Iterable<Post> findByOwnerUsername(String username);

}
