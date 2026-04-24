package com.ntt.usersManager.infrastructure.utils.exceptions;

public class UsersReadException extends RuntimeException {

    public UsersReadException(String message, Throwable cause) {
        super(message, cause);
    }
}