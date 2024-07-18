package com.theaaronrussell.soci.exception;

public class UsernameAlreadyExistsException extends Exception {

    public UsernameAlreadyExistsException() {
        super("Username already exists");
    }

}
