package com.stackroute.PostService.exception;

public class NoSuchUserExistsException extends Exception{
    public NoSuchUserExistsException(String message) {
        super(message);
    }
}
