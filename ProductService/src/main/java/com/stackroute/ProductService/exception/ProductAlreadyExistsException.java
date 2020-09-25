package com.stackroute.ProductService.exception;

public class ProductAlreadyExistsException extends Exception{
    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}
