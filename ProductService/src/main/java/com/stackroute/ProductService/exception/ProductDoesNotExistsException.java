package com.stackroute.ProductService.exception;

public class ProductDoesNotExistsException  extends Exception{
    public ProductDoesNotExistsException(String message) {
        super(message);
    }
}
