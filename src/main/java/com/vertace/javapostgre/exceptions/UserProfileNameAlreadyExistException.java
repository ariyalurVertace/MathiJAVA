package com.vertace.javapostgre.exceptions;

public class UserProfileNameAlreadyExistException extends Exception {
    public UserProfileNameAlreadyExistException(String message) {
        super(message);
    }
}