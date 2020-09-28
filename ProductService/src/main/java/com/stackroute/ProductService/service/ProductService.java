package com.stackroute.ProductService.service;



import com.stackroute.ProductService.exception.ProductAlreadyExistsException;
import com.stackroute.ProductService.exception.ProductDoesNotExistsException;
import com.stackroute.ProductService.model.Product;
import com.stackroute.ProductService.model.ProductUser;

import java.util.List;

public interface ProductService {
    public Product followProduct(Product product,String userId) throws ProductAlreadyExistsException;
    public Product unfollowProduct(String productId, String userId) throws ProductDoesNotExistsException;
    public List<Product> getAllProductByUserId(String userId) throws ProductDoesNotExistsException;
    public List<ProductUser> getAllUserByProductId(String productId) throws ProductDoesNotExistsException;

}
