package com.theaaronrussell.soci.exception;

public class UserNotFoundException extends Exception {

    /**
     * Constructs a {@code UserNotFoundException} specifying the supplied username could not be found in the database.
     *
     * @param username The username of the user that could not be found.
     */
    public UserNotFoundException(String username) {
        super(String.format("User %s not found in database", username));
    }

}
