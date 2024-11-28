package com.leucine.tech.uams.exception;

public class UserAccessManagementSystemException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor with a message.
     *
     * @param message the detailed error message
     */
    public UserAccessManagementSystemException(String message) {
        super(message);
    }
}