package com.vertace.javapostgre.exceptions;

public class SellerProfileNameAlreadyExistException extends Exception {
    public SellerProfileNameAlreadyExistException(String message) {
        super(message);
    }
}