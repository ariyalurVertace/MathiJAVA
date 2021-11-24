package com.vertace.javapostgre.exceptions;

public class UserNameAlreadyExistException extends Exception {
    public UserNameAlreadyExistException(String message) {
        super(message);
    }
}