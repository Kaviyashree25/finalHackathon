package com.stackroute.ProductService.service;



import com.stackroute.ProductService.exception.ProductAlreadyExistsException;
import com.stackroute.ProductService.exception.ProductDoesNotExistsException;
import com.stackroute.ProductService.model.Product;

import java.util.List;

public interface ProductService {
    public Product followProduct(Product product,String userId) throws ProductAlreadyExistsException, ProductDoesNotExistsException;
    public boolean unfollowProduct(String productId, String userId) throws ProductDoesNotExistsException;
    public List<Product> getAllProductByUserId(String userId) throws ProductDoesNotExistsException;

}
