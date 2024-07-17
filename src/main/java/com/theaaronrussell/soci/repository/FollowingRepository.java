package com.theaaronrussell.soci.repository;

import com.theaaronrussell.soci.entity.Following;
import com.theaaronrussell.soci.entity.FollowingId;
import org.springframework.data.repository.CrudRepository;

public interface FollowingRepository extends CrudRepository<Following, FollowingId> {

    int countByUsername(String username);

    int countByFollowing(String username);

}
