package com.theaaronrussell.soci.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "followers")
@IdClass(FollowingId.class)
public class Following {

    @Id
    @Column(name = "username")
    private String username;

    @Id
    @Column(name = "following")
    private String following;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

}
