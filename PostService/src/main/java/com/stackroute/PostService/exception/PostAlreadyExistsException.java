package com.stackroute.PostService.exception;

public class PostAlreadyExistsException extends Exception{
    public PostAlreadyExistsException(String message) {
        super(message);
    }
}
