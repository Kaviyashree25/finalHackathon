package com.stackroute.PostService.exception;

public class PostDoesNotExistsException extends Exception {
    public PostDoesNotExistsException(String message) {
        super(message);
    }
}
